package com.app.dto;

import com.app.enums.OrderStatus;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeOrderStatusDTO {
	private Long id;
	private OrderStatus orderStatus;
}
