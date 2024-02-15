package com.app.dto;

import javax.validation.constraints.NotNull;

import com.app.enums.PaymentMode;
import com.app.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	@NotNull
	private Long addressId;
	@NotNull
	private PaymentMode paymentMode;
	@NotNull
	private PaymentStatus paymentStatus;
}
