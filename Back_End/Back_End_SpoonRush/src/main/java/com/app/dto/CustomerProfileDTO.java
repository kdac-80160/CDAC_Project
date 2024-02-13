package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProfileDTO {
	@NotBlank(message = "First name cannot be blank.")
	private String firstName;
	@NotBlank(message = "Last name cannot be blank.")
	private String lastName;
	@NotBlank(message = "Mobile number cannot be blank.")
	@Size(min = 10, max = 13, message = "Mobile number should be between 10 to 13 digits.")
	private String mobileNo;
	@JsonProperty(access = Access.READ_ONLY)
	private String email;
	@JsonProperty(access = Access.READ_ONLY)
	private String message;
}
