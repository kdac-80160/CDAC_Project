package com.app.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.composite_pk.OrderAndFooditemCPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItem{
	
	@JsonIgnore
	@EmbeddedId
	private OrderAndFooditemCPK cpk;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "item_id", insertable = false, updatable = false)
	private FoodItem item;
	
	@Column(name = "qty")
	private int quantity;

	public OrderedItem(OrderAndFooditemCPK cpk, int quantity) {
		super();
		this.cpk = cpk;
		this.quantity = quantity;
	}
	
}
