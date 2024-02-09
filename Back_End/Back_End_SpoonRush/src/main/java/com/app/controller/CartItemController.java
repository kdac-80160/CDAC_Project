package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.CartItemService;

@RestController
@RequestMapping("/cart")
public class CartItemController {
	@Autowired
	private CartItemService cartService;
	
	@GetMapping("/add/{itemId}")
	public ResponseEntity<?> addItemToCart(@PathVariable Long itemId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(cartService.addItem(itemId));
	}
	
	@GetMapping("/remove/{itemId}")
	public ResponseEntity<?> removeItemFromCart(@PathVariable Long itemId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(cartService.removeItem(itemId));
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getUserCartItems()
	{
		return ResponseEntity.status(HttpStatus.OK).body(cartService.getUserCartItems());
	}
}
