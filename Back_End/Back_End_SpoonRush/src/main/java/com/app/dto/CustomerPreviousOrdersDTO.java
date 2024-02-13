package com.app.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.app.entities.Address;
import com.app.entities.OrderedItem;
import com.app.enums.OrderStatus;
import com.app.enums.PaymentMode;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerPreviousOrdersDTO {
	@JsonProperty("orderId")
	private Long id;
	private LocalDateTime orderDate;
	private double totalAmount;
	private Address address;
	private PaymentMode payMode;
	private OrderStatus orderStatus;
	private List<OrderedItem> orderedItemList = new ArrayList<OrderedItem>();
}
