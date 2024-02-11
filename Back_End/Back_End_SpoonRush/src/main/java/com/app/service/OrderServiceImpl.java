package com.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.composite_pk.OrderAndFooditemCPK;
import com.app.dao.AddressDao;
import com.app.dao.CartDao;
import com.app.dao.DeliveryLogsDao;
import com.app.dao.OrderDao;
import com.app.dao.OrderedItemDao;
import com.app.dao.UserEntityDao;
import com.app.dto.ApiResponse;
import com.app.dto.ChangeOrderStatusDTO;
import com.app.dto.CustomerOrderDetailsDTO;
import com.app.dto.DeliveryOrderDetailsDTO;
import com.app.dto.OrderDTO;
import com.app.entities.DeliveryLogs;
import com.app.entities.Order;
import com.app.entities.OrderedItem;
import com.app.entities.UserEntity;
import com.app.enums.OrderStatus;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderedItemDao orderDetailsDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private CartDao cartDao;

	@Autowired
	private UserEntityDao userDao;

	@Autowired
	private DeliveryLogsDao deliveryDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private FindAuthenticationDetails userDetails;

	@Autowired
	private EntityManager entityManager;

	@Override
	public ApiResponse createOrder(OrderDTO order) {
		Order orderEntity = new Order();
		Long userId = userDetails.getUserId();
		orderEntity.setAddress(addressDao.findById(order.getAddressId()).orElse(null));
		orderEntity.setUserInOrder(userDao.findById(userId).orElse(null));
		orderEntity.setPayMode(order.getPaymentMode());
		orderEntity.setTotalAmount(order.getTotalAmount());
		orderEntity.setOrderDate(LocalDateTime.now());
		orderEntity.setOrderStatus(OrderStatus.PENDING);
		orderEntity = orderDao.save(orderEntity);
		Long orderId = orderEntity.getId();

		entityManager.flush();

		List<OrderedItem> orderDetails = cartDao.findAllByUserInCartId(userId).stream()
				.map(c -> new OrderedItem(new OrderAndFooditemCPK(orderId, c.getCpk().getItemId()), c.getQuantity()))
				.collect(Collectors.toList());

		orderDetailsDao.saveAll(orderDetails);

		entityManager.flush();

		cartDao.deleteAllByUserInCartId(userId);

		entityManager.flush();
		return new ApiResponse("Ordered Sucessfully and status : PENDING");
	}

	@Override
	public CustomerOrderDetailsDTO getOrderDetails(Long orderId) {
		Order order = orderDao.findById(orderId).orElse(null);
		order.getOrderedItemList().size();
		return mapper.map(order, CustomerOrderDetailsDTO.class);
	}

	@Override
	public List<CustomerOrderDetailsDTO> getPendingOrders() {

		return orderDao.findAllByOrderStatus(OrderStatus.PENDING).stream()
				.map(e -> mapper.map(e, CustomerOrderDetailsDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ChangeOrderStatusDTO changeOrderStatusForDelivery(ChangeOrderStatusDTO orderStatus) {
		/*
		 * These are ordinals list for validating if a user is allowed to change the
		 * perticular status
		 */
		List<Integer> listForDelivery = List.of(6, 7, 8);
		int count = 0;
		String authority = userDetails.getAuthority();

		if (authority.equals("ROLE_DELIVERY") && listForDelivery.contains(orderStatus.getOrderStatus().ordinal())) {
			// find the delivery partner id
			Long delPartnerId = userDetails.getUserId();
			// change status in the order's table
			count = orderDao.changeOrderStatus(orderStatus.getOrderStatus(), orderStatus.getId());
			// find if there is already a delivery log
			DeliveryLogs log = deliveryDao.findByDelPartnerId(delPartnerId).orElse(null);
			// if null, means there is no delivery log then we have to generate one
			if (log == null) {
				Order order = orderDao.findById(orderStatus.getId()).orElseThrow();
				UserEntity deliveryPartner = userDao.findById(delPartnerId).orElseThrow();
				DeliveryLogs delLogs = new DeliveryLogs(order, deliveryPartner, orderStatus.getOrderStatus(),
						LocalDateTime.now());
				deliveryDao.save(delLogs);
			}
			// if it is not null, means there is already a delivery log then we have to
			// change the delivery status as well as the new time
			log.setDelStatus(orderStatus.getOrderStatus());
			log.setDeliveryLog(LocalDateTime.now());
		}
		// if none of the above if statements worked, means the current user is
		// unauthorized for the status change
		else {
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, "Unauthorized");
		}
		if (count == 1)
			return new ChangeOrderStatusDTO(orderStatus.getId(), orderStatus.getOrderStatus(), "Success");
		else
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, "Failure");
	}

	@Override
	public ChangeOrderStatusDTO changeOrderStatusForRestaurant(ChangeOrderStatusDTO orderStatus) {
		int count = 0;
		String authority = userDetails.getAuthority();
		List<Integer> listForRestaurant = List.of(2, 3, 4, 5);
		if (authority.equals("ROLE_MANAGER") && listForRestaurant.contains(orderStatus.getOrderStatus().ordinal()))
			count = orderDao.changeOrderStatus(orderStatus.getOrderStatus(), orderStatus.getId());
		else {
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, "Unauthorized");
		}
		if (count == 1)
			return new ChangeOrderStatusDTO(orderStatus.getId(), orderStatus.getOrderStatus(), "Success");
		else
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, "Failure");
	}

	@Override
	public ChangeOrderStatusDTO changeOrderStatusForCustomer(ChangeOrderStatusDTO orderStatus) {
		int count = 0;
		String authority = userDetails.getAuthority();
		List<Integer> listForCustomer = List.of(1);
		if (authority.equals("ROLE_CUSTOMER") && listForCustomer.contains(orderStatus.getOrderStatus().ordinal())) {
			// if the above condition is true that means the user has cancelled the order
			// means we have to update the orders table and the delivery logs if any!
			count = orderDao.changeOrderStatus(orderStatus.getOrderStatus(), orderStatus.getId());
			DeliveryLogs log = deliveryDao.findById(orderStatus.getId()).orElse(null);
			if(log!=null) {
				log.setDelStatus(orderStatus.getOrderStatus());
				log.setDeliveryLog(LocalDateTime.now());
			}
		} else {
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, "Unauthorized");
		}
		if (count == 1)
			return new ChangeOrderStatusDTO(orderStatus.getId(), orderStatus.getOrderStatus(), "Success");
		else
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, "Failure");
	}

	@Override
	public List<DeliveryOrderDetailsDTO> getNewOrdersForDelivery() {
		List<OrderStatus> listStatuses = List.of(OrderStatus.ACCEPTED,OrderStatus.PREPARING,OrderStatus.READY_FOR_DELIVERY);
		return null;
	}

	@Override
	public List<DeliveryOrderDetailsDTO> getOngoingOrdersForDelivery() {
		
		return null;
	}

	@Override
	public List<DeliveryOrderDetailsDTO> getDeliveredOrders() {
		
		return null;
	}

}
