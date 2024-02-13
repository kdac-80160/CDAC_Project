package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ChangeOrderStatusDTO;
import com.app.dto.CustomerOrderDetailsDTO;
import com.app.dto.DeliveryOrderDetailsDTO;
import com.app.dto.OrderDTO;
import com.app.dto.RestaurantOrderDetailsDTO;
import com.app.enums.OrderStatus;

public interface OrderService {
	// This is for customer.
	ApiResponse createOrder(OrderDTO order);
	// this is also for customer
	CustomerOrderDetailsDTO getOrderDetails(Long orderId);
	// this is for customer
	List<CustomerOrderDetailsDTO> getPreviousOrdersForCustomer();
	// below is for customer
	List<CustomerOrderDetailsDTO> getUpcomingOrdersForCustomer();
	// change order status for customer
	ChangeOrderStatusDTO changeOrderStatusForCustomer(ChangeOrderStatusDTO orderStatus);
	// pending orders for restaurant
	List<CustomerOrderDetailsDTO> getPendingOrdersForCustomer();
	// change order status for restaurant
	ChangeOrderStatusDTO changeOrderStatusForRestaurant(ChangeOrderStatusDTO orderStatus);
	// change order status for delivery
	ChangeOrderStatusDTO changeOrderStatusForDelivery(ChangeOrderStatusDTO orderStatus);
	// below all are for delivery boy
	List<DeliveryOrderDetailsDTO> getNewOrdersForDelivery();
	List<DeliveryOrderDetailsDTO> getOngoingOrdersForDelivery();
	List<DeliveryOrderDetailsDTO> getDeliveredOrders();
	// below all are for restaurant
	List<RestaurantOrderDetailsDTO> getPendingOrdersForRestaurant();
	List<RestaurantOrderDetailsDTO> getDeliveredOrdersForRestaurant();
	List<RestaurantOrderDetailsDTO> getCancelledOrdersForRestaurant();
	List<RestaurantOrderDetailsDTO> getAllOrdersForRestaurant();
	List<RestaurantOrderDetailsDTO> getAllByOrderStatus(OrderStatus status);
}
