package com.app.composite_pk;

import java.io.Serializable;
import java.util.Objects;

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
	@Override
	public int hashCode() {
		return Objects.hash(itemId, orderId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderAndFooditemCPK other = (OrderAndFooditemCPK) obj;
		return Objects.equals(itemId, other.itemId) && Objects.equals(orderId, other.orderId);
	}
	
	
	
}
