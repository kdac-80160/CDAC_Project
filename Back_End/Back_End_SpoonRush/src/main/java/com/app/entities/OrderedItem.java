package com.app.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.composite_pk.OrderAndFooditemCPK;

import lombok.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItem{
	
	@EmbeddedId
	private OrderAndFooditemCPK cpk;
	
	@ManyToOne
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "item_id", insertable = false, updatable = false)
	private FoodItem item;
	
	@Column(name = "qty")
	private int quantity;
}
