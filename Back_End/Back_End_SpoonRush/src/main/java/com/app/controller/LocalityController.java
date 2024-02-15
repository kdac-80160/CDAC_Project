package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.LocalityService;

@RestController
@RequestMapping("/locality")
public class LocalityController {
	@Autowired
	private LocalityService localityService;
	
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllLocalities()
	{
		return ResponseEntity.status(HttpStatus.OK).body(localityService.getLocalities());
	}
}
