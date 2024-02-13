package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ChangeOrderStatusDTO;
import com.app.dto.OrderDTO;
import com.app.enums.OrderStatus;
import com.app.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/customer/create")
	public ResponseEntity<?> createOrder(@RequestBody OrderDTO order)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
	}
	
	@PostMapping("/customer/change-status")
	public ResponseEntity<?> changeOrderStatusForCustomer(@RequestBody ChangeOrderStatusDTO orderStatus)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.changeOrderStatusForCustomer(orderStatus));
	}
	
	@GetMapping("/customer/upcoming")
	public ResponseEntity<?> getUpcomingOrders()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getUpcomingOrders());
	}
	
	@GetMapping("/customer/previous")
	public ResponseEntity<?> getPreviousOrders()
	{
		return null;
	}
	
	
	@GetMapping("/restaurant/get-by-status")
	public ResponseEntity<?> getOrdersByStatus(@RequestBody OrderStatus orderStatus)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getPendingOrders());
	}
	
	@GetMapping("/restaurant/pending")
	public ResponseEntity<?> getPendingOrders()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getPendingOrders());
	}
	
	
	@PostMapping("/restaurant/change-status")
	public ResponseEntity<?> changeOrderStatusForRestaurant(@RequestBody ChangeOrderStatusDTO orderStatus)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.changeOrderStatusForRestaurant(orderStatus));
	}
	
	@GetMapping("/delivery/new-orders")
	public ResponseEntity<?> getNewOrders()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getNewOrdersForDelivery());
	}
	
	@GetMapping("/delivery/on-going")
	public ResponseEntity<?> getOngoingOrders()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOngoingOrdersForDelivery());
	}
	
	@GetMapping("/delivery/delivered")
	public ResponseEntity<?> getDeliveredOrders()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getDeliveredOrders());
	}
	
	@PostMapping("/delivery/change-status")
	public ResponseEntity<?> changeOrderStatusForDelivery(@RequestBody ChangeOrderStatusDTO orderStatus)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.changeOrderStatusForDelivery(orderStatus));
	}
 	
}
