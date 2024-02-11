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
import com.app.dao.OrderDao;
import com.app.dao.OrderedItemDao;
import com.app.dao.UserEntityDao;
import com.app.dto.ApiResponse;
import com.app.dto.ChangeOrderStatusDTO;
import com.app.dto.CustomerOrderDetailsDTO;
import com.app.dto.OrderDTO;
import com.app.entities.Order;
import com.app.entities.OrderedItem;
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
		
		List<OrderedItem> orderDetails = cartDao.findAllByUserInCartId(userId)
										.stream()
										.map(c -> 
										new OrderedItem(new OrderAndFooditemCPK(orderId,c.getCpk().getItemId()),c.getQuantity()))
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
		
		return orderDao.findAllByOrderStatus(OrderStatus.PENDING)
				.stream()
				.map(e -> mapper.map(e, CustomerOrderDetailsDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse changeOrderStatus(ChangeOrderStatusDTO orderStatus) {
		//These are ordinals list for validating if a user is allowed to change the perticular status
		List<Integer> listForCustomer = List.of(1);
		List<Integer> listForDelivery = List.of(6,7,8);
		List<Integer> listForRestaurant = List.of(2,3,4,5);
		int count = 0;
		String authority = userDetails.getAuthority();
		if(authority.equals("ROLE_MANAGER") && listForRestaurant.contains(orderStatus.getOrderStatus().ordinal()))
			count = orderDao.changeOrderStatus(orderStatus.getOrderStatus(), orderStatus.getId());
		else if(authority.equals("ROLE_CUSTOMER") && listForCustomer.contains(orderStatus.getOrderStatus().ordinal()))
			count = orderDao.changeOrderStatus(orderStatus.getOrderStatus(), orderStatus.getId());
		else if(authority.equals("ROLE_DELIVERY") && listForDelivery.contains(orderStatus.getOrderStatus().ordinal()))
			count = orderDao.changeOrderStatus(orderStatus.getOrderStatus(), orderStatus.getId());
		else
		{
			return new ApiResponse("You are not authorized for this action.");
		}
		if(count == 1)
			return new ApiResponse("Order status changed to "+orderStatus.getOrderStatus().toString());
		else
		return new ApiResponse("Some error has occured.");
	}
	
}
