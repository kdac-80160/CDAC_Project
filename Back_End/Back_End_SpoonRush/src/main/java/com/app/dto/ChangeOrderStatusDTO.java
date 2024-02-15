package com.app.dto;

import javax.validation.constraints.NotNull;

import com.app.enums.OrderStatus;
import com.app.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeOrderStatusDTO {
	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long id;
	@NotNull
	@JsonProperty(access = Access.READ_WRITE)
	private OrderStatus orderStatus;
	
	private PaymentStatus paymentStatus;
	@JsonProperty(access = Access.READ_ONLY)
	private String message;
}
