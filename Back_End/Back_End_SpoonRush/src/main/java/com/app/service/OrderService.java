package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ChangeOrderStatusDTO;
import com.app.dto.CustomerOrderDetailsDTO;
import com.app.dto.OrderDTO;

public interface OrderService {
	ApiResponse createOrder(OrderDTO order);
	CustomerOrderDetailsDTO getOrderDetails(Long orderId);
	List<CustomerOrderDetailsDTO> getPendingOrders();
	ChangeOrderStatusDTO changeOrderStatusForRestaurant(ChangeOrderStatusDTO orderStatus);
	ChangeOrderStatusDTO changeOrderStatusForCustomer(ChangeOrderStatusDTO orderStatus);
	ChangeOrderStatusDTO changeOrderStatusForDelivery(ChangeOrderStatusDTO orderStatus);
}
