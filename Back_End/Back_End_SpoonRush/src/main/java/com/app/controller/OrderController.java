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
	
	@PostMapping("/customer/changestatus")
	public ResponseEntity<?> changeOrderStatusForCustomer(@RequestBody ChangeOrderStatusDTO orderStatus)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.changeOrderStatusForCustomer(orderStatus));
	}
	
	@GetMapping("/customer/details/{orderId}")
	public ResponseEntity<?> getOrderDetails(@PathVariable Long orderId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderDetails(orderId));
	}
	
	
	@GetMapping("/restaurant/pending")
	public ResponseEntity<?> getPendingOrders()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getPendingOrders());
	}
	
	@PostMapping("/restaurant/changestatus")
	public ResponseEntity<?> changeOrderStatusForRestaurant(@RequestBody ChangeOrderStatusDTO orderStatus)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.changeOrderStatusForRestaurant(orderStatus));
	}
	
	@PostMapping("/delivery/changestatus")
	public ResponseEntity<?> changeOrderStatusForDelivery(@RequestBody ChangeOrderStatusDTO orderStatus)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.changeOrderStatusForDelivery(orderStatus));
	}
 	
}
