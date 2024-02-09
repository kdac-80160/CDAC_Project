package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.FoodItemService;

@RestController
@RequestMapping("/fooditems")
public class FoodItemController {
	@Autowired
	private FoodItemService foodItemService;
	
	@GetMapping("/popular")
	public ResponseEntity<?> getPopolarFoods()
	{
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getPopularFoods());
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAll()
	{
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getAll());
	}
}
