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
import com.app.custom_exceptions.ResourceNotFoundException;
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
		orderEntity.setPayStatus(order.getPaymentStatus());
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

	// this is for restaurant to get the pending orders..
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

		if (listForDelivery.contains(orderStatus.getOrderStatus().ordinal())) {
			// find the delivery partner id
			Long delPartnerId = userDetails.getUserId();
			// find persistent entity
			Order order = orderDao.findById(orderStatus.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Order does not exist."));
			if (orderStatus.getOrderStatus() == OrderStatus.WAITING) {
				if (order.getDelInOrder() == null) {
					UserEntity deliveryPartner = userDao.findById(delPartnerId).orElse(null);
					order.setDelInOrder(deliveryPartner);
					order.setOrderLog(LocalDateTime.now());
					DeliveryLogs delLogs = new DeliveryLogs(order, deliveryPartner, orderStatus.getOrderStatus(),
							LocalDateTime.now());
					deliveryDao.save(delLogs);
					return new ChangeOrderStatusDTO(orderStatus.getId(), orderStatus.getOrderStatus(), "Success");
				} else {
					return new ChangeOrderStatusDTO(orderStatus.getId(), null, "Already Assigned");
				}
			} else {
				order.setOrderStatus(orderStatus.getOrderStatus());
				order.setOrderLog(LocalDateTime.now());
				DeliveryLogs log = deliveryDao.findByDelPartnerId(delPartnerId).orElse(null);
				if (log != null) {
					log.setDelStatus(orderStatus.getOrderStatus());
					log.setDeliveryLog(LocalDateTime.now());
				}
				return new ChangeOrderStatusDTO(orderStatus.getId(), orderStatus.getOrderStatus(), "Success");
			}
		} else {
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, "Unauthorized");
		}

	}

	@Override
	public ChangeOrderStatusDTO changeOrderStatusForRestaurant(ChangeOrderStatusDTO orderStatus) {
		int count = 0;
		List<Integer> listForRestaurant = List.of(2, 3, 4, 5);
		if (listForRestaurant.contains(orderStatus.getOrderStatus().ordinal())) 
		{
			Order order = orderDao.findById(orderStatus.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Order does not exist."));
			order.setOrderStatus(orderStatus.getOrderStatus());
			order.setOrderLog(LocalDateTime.now());
			return new ChangeOrderStatusDTO(orderStatus.getId(), orderStatus.getOrderStatus(), "Success");
		}
		else {
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, "Unauthorized");
		}
	}

	@Override
	public ChangeOrderStatusDTO changeOrderStatusForCustomer(ChangeOrderStatusDTO orderStatus) {
		int count = 0;
		List<Integer> listForCustomer = List.of(1);
		if (listForCustomer.contains(orderStatus.getOrderStatus().ordinal())) {
			// if the above condition is true that means the user has cancelled the order
			// means we have to update the orders table and the delivery logs if any!
			Order order = orderDao.findById(orderStatus.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Order does not exist."));
			order.setOrderStatus(orderStatus.getOrderStatus());
			order.setOrderLog(LocalDateTime.now());
			DeliveryLogs log = deliveryDao.findById(orderStatus.getId()).orElse(null);
			if (log != null) {
				log.setDelStatus(orderStatus.getOrderStatus());
				log.setDeliveryLog(LocalDateTime.now());
			}
			return new ChangeOrderStatusDTO(orderStatus.getId(), orderStatus.getOrderStatus(), "Success");
		} else {
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, "Unauthorized");
		}
	}

	@Override
	public List<DeliveryOrderDetailsDTO> getNewOrdersForDelivery() {
		List<OrderStatus> listStatuses = List.of(OrderStatus.ACCEPTED, OrderStatus.PREPARING,
				OrderStatus.READY_FOR_DELIVERY);
		return orderDao.findAllByOrderStatusesAndDelPartnerIdIsNull(listStatuses).stream()
				.map(o -> mapper.map(o, DeliveryOrderDetailsDTO.class)).collect(Collectors.toList());
	}

	// For delivery guy
	@Override
	public List<DeliveryOrderDetailsDTO> getOngoingOrdersForDelivery() {

		return orderDao.findAllByOrderStatusesAndDelPartnerId(List.of(OrderStatus.WAITING,OrderStatus.ON_THE_WAY),
				userDetails.getUserId()).
				stream()
				.map(o -> mapper.map(o, DeliveryOrderDetailsDTO.class)).collect(Collectors.toList());
	}

	// For delivery guy
	@Override
	public List<DeliveryOrderDetailsDTO> getDeliveredOrders() {

		return orderDao.findAllByOrderStatusAndDelInOrderId(OrderStatus.DELIVERED,userDetails.getUserId())
				.stream()
				.map(o -> mapper.map(o, DeliveryOrderDetailsDTO.class))
				.collect(Collectors.toList());
	}
	
	// For Customer
	@Override
	public List<CustomerOrderDetailsDTO> getUpcomingOrders() {
		//EXCEPT => CANCELLED, REJECTED, DELIVERED
		List<OrderStatus> listStatuses = List.of(OrderStatus.CANCELLED, OrderStatus.REJECTED,
				OrderStatus.DELIVERED);
		return orderDao.findAllByExcpetOrderStatusesAndUserId(listStatuses, userDetails.getUserId())
				.stream()
				.map(o -> mapper.map(o, CustomerOrderDetailsDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<CustomerOrderDetailsDTO> getPreviousOrders() {
		
		return null;
	}

}
