package com.app.composite_pk;

import java.io.Serializable;

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
}
