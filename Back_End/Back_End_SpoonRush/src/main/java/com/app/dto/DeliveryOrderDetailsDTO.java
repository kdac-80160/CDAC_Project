package com.app.dto;

import com.app.entities.Address;
import com.app.enums.OrderStatus;
import com.app.enums.PaymentMode;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderDetailsDTO {
	@JsonProperty(value = "orderId")
	private Long id;
	private String firstName;
	private String lastName;
	private Address address;
	private double totalAmount;
	private OrderStatus orderStatus;
	private PaymentMode payMode;
}
