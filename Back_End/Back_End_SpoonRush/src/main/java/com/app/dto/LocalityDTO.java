package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocalityDTO {
	@NotNull
	private Long id;
	@NotBlank
	private String localityName;
	@NotNull
	private int pincode;
	@NotBlank
	private String city;
	@NotBlank
	private String state;
}
