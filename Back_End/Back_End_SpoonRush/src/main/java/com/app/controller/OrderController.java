package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	
	@PostMapping("/customer/place-order")
	public ResponseEntity<?> createOrderForCustomer(@RequestBody @Valid OrderDTO order)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
	}
	
	@PatchMapping("/customer/cancel/{orderId}")
	public ResponseEntity<?> changeOrderStatusForCustomer(@PathVariable @Valid Long orderId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrderForCustomer(orderId));
	}
	
	@GetMapping("/customer/upcoming")
	public ResponseEntity<?> getUpcomingOrdersForCustomer()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getUpcomingOrdersForCustomer());
	}
	
	@GetMapping("/customer/previous")
	public ResponseEntity<?> getPreviousOrdersForCustomer()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getPreviousOrdersForCustomer());
	}
	
	
	@GetMapping("/restaurant/get-by-status/{status}")
	public ResponseEntity<?> getOrdersByStatusForRestaurant(@PathVariable String status)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllByOrderStatus(OrderStatus.valueOf(status.toUpperCase())));
	}
	
	@GetMapping("/restaurant/pending")
	public ResponseEntity<?> getPendingOrdersForRestaurant()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getPendingOrdersForRestaurant());
	}
	
	@GetMapping("/restaurant/ongoing")
	public ResponseEntity<?> getOngoingOrdersForRestaurant()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOngoingOrdersForRestaurant());
	}
	
	@GetMapping("/restaurant/delivered")
	public ResponseEntity<?> getDeliveredOrdersForRestaurant()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getDeliveredOrdersForRestaurant());
	}
	
	@GetMapping("/restaurant/cancelled")
	public ResponseEntity<?> getCancelledOrdersForRestaurant()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getCancelledOrdersForRestaurant());
	}
	
	@PostMapping("/restaurant/change-status")
	public ResponseEntity<?> changeOrderStatusForRestaurant(@RequestBody @Valid ChangeOrderStatusDTO orderStatus)
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
