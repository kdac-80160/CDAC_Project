package com.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.composite_pk.OrderAndFooditemCPK;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.AddressDao;
import com.app.dao.CartDao;
import com.app.dao.DeliveryLogsDao;
import com.app.dao.OrderDao;
import com.app.dao.OrderedItemDao;
import com.app.dao.PaymentDao;
import com.app.dao.UserEntityDao;
import com.app.dto.ApiResponse;
import com.app.dto.ChangeOrderStatusDTO;
import com.app.dto.CustomerOrderDetailsDTO;
import com.app.dto.DeliveryOrderDetailsDTO;
import com.app.dto.OrderDTO;
import com.app.dto.RestaurantOrderDetailsDTO;
import com.app.entities.CartItem;
import com.app.entities.DeliveryLogs;
import com.app.entities.Order;
import com.app.entities.OrderedItem;
import com.app.entities.Payment;
import com.app.entities.UserEntity;
import com.app.enums.OrderStatus;
import com.app.enums.PaymentStatus;
import com.app.enums.ResponseStatus;
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
	private PaymentDao paymentDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private FindAuthenticationDetails userDetails;

	@Autowired
	private EntityManager entityManager;

	@Value("${gst.value}")
	private double gst;

	@Override
	public ApiResponse createOrder(OrderDTO order) {

		// first find the userId
		Long userId = userDetails.getUserId();

		// find cartitems
		List<CartItem> cartItems = cartDao.findAllByUserInCartId(userId);

		// calculate total price from the cart items using stream
		double totalAmount = cartItems.stream().map(c -> c.getItem().getPrice() * c.getQuantity())
				.reduce((a, b) -> a + b).get();
		totalAmount = totalAmount + totalAmount * gst;
		
		System.out.println("Amount calculated : " + totalAmount);
		// create new order
		
		Order orderEntity = new Order();

		orderEntity.setAddress(addressDao.findById(order.getAddressId()).orElse(null));
		orderEntity.setUserInOrder(userDao.findById(userId).orElse(null));
		orderEntity.setPayStatus(order.getPaymentStatus());
		orderEntity.setPayMode(order.getPaymentMode());
		// set the total amount calculated above
		orderEntity.setTotalAmount(totalAmount);
		orderEntity.setOrderDate(LocalDateTime.now());
		orderEntity.setOrderStatus(OrderStatus.PENDING);
		orderEntity = orderDao.save(orderEntity);
		Long orderId = orderEntity.getId();

		entityManager.flush();
		// Get list of order details objects
		List<OrderedItem> orderDetails = cartItems.stream()
				.map(c -> new OrderedItem(new OrderAndFooditemCPK(orderId, c.getCpk().getItemId()), c.getQuantity()))
				.collect(Collectors.toList());

		orderDetailsDao.saveAll(orderDetails);

		entityManager.flush();

		cartDao.deleteAllByUserInCartId(userId);

		entityManager.flush();
		return new ApiResponse("Ordered Sucessfully and status : PENDING",ResponseStatus.SUCCESS);
	}

	@Override
	public CustomerOrderDetailsDTO getOrderDetails(Long orderId) {
		Order order = orderDao.findById(orderId).orElse(null);
		order.getOrderedItemList().size();
		return mapper.map(order, CustomerOrderDetailsDTO.class);
	}

	// this is for restaurant to get the pending orders..
	@Override
	public List<CustomerOrderDetailsDTO> getPendingOrdersForCustomer() {

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
					return new ChangeOrderStatusDTO(orderStatus.getId(), orderStatus.getOrderStatus(),
							orderStatus.getPaymentStatus(), "Success");
				} else {
					return new ChangeOrderStatusDTO(orderStatus.getId(), null, orderStatus.getPaymentStatus(),
							"Already Assigned");
				}
			} else {
				order.setOrderStatus(orderStatus.getOrderStatus());
				order.setOrderLog(LocalDateTime.now());
				DeliveryLogs log = deliveryDao.findById(orderStatus.getId()).orElse(null);
				if (log != null) {
					log.setDelStatus(orderStatus.getOrderStatus());
					log.setDeliveryLog(LocalDateTime.now());
				}
				if (orderStatus.getOrderStatus() == OrderStatus.DELIVERED) {
					order.setPayStatus(orderStatus.getPaymentStatus());
					Payment payment = new Payment();
					payment.setOrder(order);
					payment.setAmountPaid(order.getTotalAmount());
					payment.setPaymentMode(order.getPayMode());
					payment.setPaymentTime(LocalDateTime.now());
					payment.setStatus(orderStatus.getPaymentStatus());
					paymentDao.save(payment);
				}
				return new ChangeOrderStatusDTO(orderStatus.getId(), orderStatus.getOrderStatus(),
						orderStatus.getPaymentStatus(), "Success");
			}
		} else {
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, orderStatus.getPaymentStatus(), "Unauthorized");
		}

	}

	@Override
	public ChangeOrderStatusDTO changeOrderStatusForRestaurant(ChangeOrderStatusDTO orderStatus) {
		int count = 0;
		List<Integer> listForRestaurant = List.of(2, 3, 4, 5);
		if (listForRestaurant.contains(orderStatus.getOrderStatus().ordinal())) {
			Order order = orderDao.findById(orderStatus.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Order does not exist."));
			order.setOrderStatus(orderStatus.getOrderStatus());
			order.setOrderLog(LocalDateTime.now());
			return new ChangeOrderStatusDTO(orderStatus.getId(), orderStatus.getOrderStatus(),
					orderStatus.getPaymentStatus(), "Success");
		} else {
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, orderStatus.getPaymentStatus(), "Unauthorized");
		}
	}
	
	@Override
	public ApiResponse cancelOrderForCustomer(Long orderId) {
		// find the order first, if the given order id does not belong to the user throw exception
		Order order = orderDao.findByIdAndUserInOrderId(orderId, userDetails.getUserId())
					  .orElseThrow(()-> new ResourceNotFoundException("No such order exists."));
		order.setOrderStatus(OrderStatus.CANCELLED);
		order.setOrderLog(LocalDateTime.now());
		
		// check if there is any delivery log for the same, if yes then update
		DeliveryLogs log = deliveryDao.findById(orderId).orElse(null);
		if (log != null) {
			log.setDelStatus(OrderStatus.CANCELLED);
			log.setDeliveryLog(LocalDateTime.now());
		}
		return new ApiResponse("Order cancelled successfully.", ResponseStatus.SUCCESS);
	}

	@Override
	public ChangeOrderStatusDTO changeOrderStatusForCustomer(ChangeOrderStatusDTO orderStatus) {
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
			return new ChangeOrderStatusDTO(orderStatus.getId(), orderStatus.getOrderStatus(),
					orderStatus.getPaymentStatus(), "Success");
		} else {
			return new ChangeOrderStatusDTO(orderStatus.getId(), null, orderStatus.getPaymentStatus(), "Unauthorized");
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

		return orderDao
				.findAllByOrderStatusesAndDelPartnerId(List.of(OrderStatus.WAITING,OrderStatus.PREPARING,OrderStatus.ACCEPTED,
						OrderStatus.READY_FOR_DELIVERY,OrderStatus.ON_THE_WAY),
						userDetails.getUserId())
				.stream().map(o -> mapper.map(o, DeliveryOrderDetailsDTO.class)).collect(Collectors.toList());
	}

	// For delivery guy
	@Override
	public List<DeliveryOrderDetailsDTO> getDeliveredOrders() {

		return orderDao.findAllByOrderStatusAndDelInOrderId(OrderStatus.DELIVERED, userDetails.getUserId()).stream()
				.map(o -> mapper.map(o, DeliveryOrderDetailsDTO.class)).collect(Collectors.toList());
	}

	// For Customer
	@Override
	public List<CustomerOrderDetailsDTO> getUpcomingOrdersForCustomer() {
		// EXCEPT => CANCELLED, REJECTED, DELIVERED
		List<OrderStatus> listStatuses = List.of(OrderStatus.CANCELLED, OrderStatus.REJECTED, OrderStatus.DELIVERED);
		return orderDao.findAllByExcpetOrderStatusesAndUserId(listStatuses, userDetails.getUserId()).stream()
				.map(o -> mapper.map(o, CustomerOrderDetailsDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<CustomerOrderDetailsDTO> getPreviousOrdersForCustomer() {
		System.out.println(userDetails.getUserId());
		return orderDao
				.findAllByOrderStatusesAndUserId(List.of(OrderStatus.CANCELLED, OrderStatus.DELIVERED),
						userDetails.getUserId())
				.stream().map(o -> mapper.map(o, CustomerOrderDetailsDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<RestaurantOrderDetailsDTO> getPendingOrdersForRestaurant() {
		return orderDao.findAllByOrderStatus(OrderStatus.PENDING).stream()
				.map(o -> mapper.map(o, RestaurantOrderDetailsDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<RestaurantOrderDetailsDTO> getDeliveredOrdersForRestaurant() {
		return orderDao.findAllByOrderStatus(OrderStatus.DELIVERED).stream()
				.map(o -> mapper.map(o, RestaurantOrderDetailsDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<RestaurantOrderDetailsDTO> getCancelledOrdersForRestaurant() {
		return orderDao.findAllByOrderStatus(OrderStatus.CANCELLED).stream()
				.map(o -> mapper.map(o, RestaurantOrderDetailsDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<RestaurantOrderDetailsDTO> getAllOrdersForRestaurant() {
		return orderDao.findAll().stream().map(o -> mapper.map(o, RestaurantOrderDetailsDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<RestaurantOrderDetailsDTO> getAllByOrderStatus(OrderStatus status) {

		return orderDao.findAllByOrderStatus(status).stream().map(o -> mapper.map(o, RestaurantOrderDetailsDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<RestaurantOrderDetailsDTO> getOngoingOrdersForRestaurant() {
		return orderDao.findAllByExceptOrderStatuses(List.of
				(OrderStatus.DELIVERED,OrderStatus.CANCELLED,OrderStatus.REJECTED,OrderStatus.PENDING))
				.stream()
				.map(o -> mapper.map(o, RestaurantOrderDetailsDTO.class))
				.collect(Collectors.toList());
	}


}
