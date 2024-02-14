package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddFoodItemDTO;
import com.app.service.FoodItemService;

@RestController
@RequestMapping("/fooditems")
public class FoodItemController {
	@Autowired
	private FoodItemService foodItemService;
	
	@GetMapping("/popular")
	public ResponseEntity<?> getPopularFoods()
	{
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getPopularFoods());
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAll()
	{
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getAll());
	}
	
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@PostMapping(value = "/add", consumes = "multipart/form-data")
	public ResponseEntity<?> addItem(@ModelAttribute @Valid AddFoodItemDTO foodItem)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(foodItemService.addFoodItem(foodItem));
	}
}
