package com.app.composite_pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAndFooditemCPK implements Serializable{
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "item_id")
	private Long itemId;
	@Override
	public int hashCode() {
		return Objects.hash(itemId, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAndFooditemCPK other = (UserAndFooditemCPK) obj;
		return Objects.equals(itemId, other.itemId) && Objects.equals(userId, other.userId);
	}
	
	
}
