package com.app.dto;

import com.app.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeOrderStatusDTO {
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long id;
	@JsonProperty(access = Access.READ_WRITE)
	private OrderStatus orderStatus;
	@JsonProperty(access = Access.READ_ONLY)
	private String message;
}
