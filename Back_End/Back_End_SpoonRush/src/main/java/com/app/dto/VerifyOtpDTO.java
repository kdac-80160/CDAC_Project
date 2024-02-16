package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerifyOtpDTO {
	@NotBlank(message = "Email is required.")
	private String email;
	@NotBlank(message = "OTP is required.")
	@Size(min = 6, max = 6, message = "OTP should be of 6 digits")
	private String otp;
}
