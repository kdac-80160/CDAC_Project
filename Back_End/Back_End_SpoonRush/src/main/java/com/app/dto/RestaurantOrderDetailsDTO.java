package com.app.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.app.entities.Address;
import com.app.entities.OrderedItem;
import com.app.enums.OrderStatus;
import com.app.enums.PaymentMode;
import com.app.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantOrderDetailsDTO {
	@JsonProperty("orderId")
	private Long id;
	private String userName;
	private LocalDateTime orderDate;
	private LocalDateTime orderLog;
	private double totalAmount;
	private Address address;
	private PaymentMode payMode;
	private PaymentStatus payStatus;
	private OrderStatus orderStatus;
	private List<OrderedItem> orderedItemList = new ArrayList<OrderedItem>();
	private String deliveryGuyName;
	private String deliveryGuyNumber;
}
