package com.app.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.app.entities.Address;
import com.app.entities.OrderedItem;
import com.app.enums.OrderStatus;
import com.app.enums.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderDetailsDTO {
	private LocalDateTime orderDate;
	private double totalAmount;
	private Address address;
	private PaymentMode payMode;
	private OrderStatus orderStatus;
	private List<OrderedItem> orderedItemList = new ArrayList<OrderedItem>();
}
