package com.app.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@AttributeOverride(name = "id", column = @Column(name = "del_id"))
@Entity
public class DeliveryPartner extends UserEntity {
	
}
