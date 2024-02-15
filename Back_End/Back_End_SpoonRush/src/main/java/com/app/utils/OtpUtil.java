package com.app.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class OtpUtil {
	
	public String generateOTP()
	{
		int max = 999999;
		int min = 100000;
		Random random = new Random();
		int randomNumber = random.nextInt(max-min+1) + min;
		return Integer.toString(randomNumber);
	}
}
