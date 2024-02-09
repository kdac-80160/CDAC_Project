package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.CustomerOrderDetailsDTO;
import com.app.dto.OrderDTO;

public interface OrderService {
	ApiResponse createOrder(OrderDTO order);
	CustomerOrderDetailsDTO getOrderDetails(Long orderId);
}
