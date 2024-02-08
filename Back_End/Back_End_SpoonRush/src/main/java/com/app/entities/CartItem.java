package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.composite_pk.UserAndFooditemCPK;

import lombok.*;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem{
	
	@EmbeddedId
	private UserAndFooditemCPK cpk;
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity userInCart;
	@ManyToOne
	@JoinColumn(name = "item_id", insertable = false, updatable = false)
	private FoodItem item;
	
	private int quantity;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime addTime;
	public CartItem(UserAndFooditemCPK cpk, int quantity, LocalDateTime addTime) {
		super();
		this.cpk = cpk;
		this.quantity = quantity;
		this.addTime = addTime;
	}
	
	@Override
	public String toString() {
		return "CartItem [item=" + item + ", quantity=" + quantity + "]";
	}
	
}
