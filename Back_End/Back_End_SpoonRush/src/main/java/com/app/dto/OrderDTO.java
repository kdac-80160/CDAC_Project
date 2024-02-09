package com.app.dto;

import com.app.enums.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private double totalAmount;
	private Long addressId;
	private PaymentMode paymentMode;
}
