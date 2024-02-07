package com.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	
	@RequestMapping("/")
	public String allCheck()
	{
		return "All Check!";
	}
	
	@RequestMapping("/cart/payment")
	public String customerCheck()
	{
		return "I am customer!";
	}
	
	@RequestMapping("/admin")
	public String adminCheck()
	{
		return "Admin check";
	}

}
