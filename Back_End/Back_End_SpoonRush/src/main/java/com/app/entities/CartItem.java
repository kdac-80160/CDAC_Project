package com.app.entities;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.*;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userInCart;
	@ManyToOne
	@JoinColumn(name = "item_id")
	private FoodItem item;
	
	private int quantity;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate addTime;
}
