package com.app.composite_pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.app.entities.FoodItem;
import com.app.entities.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderAndFooditemCPK implements Serializable {
	@Column(name = "order_id")
	private Long orderId;
	@Column(name = "item_id")
	private Long itemId;
	
}
