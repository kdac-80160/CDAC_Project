package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddressDTO;
import com.app.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllAddresses()
	{
		return ResponseEntity.status(HttpStatus.OK).body(addService.getAddressesForUser());
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addAddress(@RequestBody @Valid AddressDTO address)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(addService.addAddress(address));
	}
}
