package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "delivery_partners")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryPartner extends UserEntity {
	@Lob
	private byte[] aadharCard;
	
	@Column(length = 200)
	private String picturePath;
	
	public DeliveryPartner(byte[] aadharCard, String picturePath) {
		this.aadharCard = aadharCard;
		this.picturePath = picturePath;
	}
	
	
}
