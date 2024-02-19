package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerProfileDTO;
import com.app.service.UserProfileService;

@RestController
@RequestMapping("/users")
public class UserProfileController {
	
	@Autowired
	private UserProfileService userService;
	
	@GetMapping("/customer/get-profile")
	public ResponseEntity<?> getCustomerProfile()
	{
		return ResponseEntity.status(HttpStatus.OK).body(userService.getCustomerProfile());
	}
	
	@PutMapping("/customer/update-profile")
	public ResponseEntity<?> updateCustomerProfile(@RequestBody @Valid CustomerProfileDTO profileDTO)
	{
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateCustomerProfile(profileDTO));
	}
	
	@GetMapping("/restaurant/get-profile")
	public ResponseEntity<?> getRestaurantProfile()
	{
		return ResponseEntity.status(HttpStatus.OK).body(userService.getCustomerProfile());
	}
}
