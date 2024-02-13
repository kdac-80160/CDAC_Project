package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ChangeOrderStatusDTO;
import com.app.dto.CustomerOrderDetailsDTO;
import com.app.dto.DeliveryOrderDetailsDTO;
import com.app.dto.OrderDTO;

public interface OrderService {
	ApiResponse createOrder(OrderDTO order);
	// this is also for customer
	CustomerOrderDetailsDTO getOrderDetails(Long orderId);
	List<CustomerOrderDetailsDTO> getPreviousOrders();
	// below is for customer
	List<CustomerOrderDetailsDTO> getUpcomingOrders();
	// pending orders for restaurant
	List<CustomerOrderDetailsDTO> getPendingOrders();
	// change order status for restaurant
	ChangeOrderStatusDTO changeOrderStatusForRestaurant(ChangeOrderStatusDTO orderStatus);
	// change order status for customer
	ChangeOrderStatusDTO changeOrderStatusForCustomer(ChangeOrderStatusDTO orderStatus);
	// change order status for delivery
	ChangeOrderStatusDTO changeOrderStatusForDelivery(ChangeOrderStatusDTO orderStatus);
	// below all are for delivery boy
	List<DeliveryOrderDetailsDTO> getNewOrdersForDelivery();
	List<DeliveryOrderDetailsDTO> getOngoingOrdersForDelivery();
	List<DeliveryOrderDetailsDTO> getDeliveredOrders();
}
