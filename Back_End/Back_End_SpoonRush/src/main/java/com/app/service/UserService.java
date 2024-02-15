package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.ResetPasswordDTO;
import com.app.dto.Signup;

public interface UserService {
//sign up
	Signup userRegistration(Signup reqDTO);
	ApiResponse otpGeneration(String email);
	ApiResponse resetPassword(ResetPasswordDTO resetPassDTO);
}
