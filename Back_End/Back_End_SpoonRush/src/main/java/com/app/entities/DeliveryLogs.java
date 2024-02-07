package com.app.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.app.enums.OrderStatus;

import lombok.*;

@Entity
@Table(name = "delivery_logs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryLogs implements Serializable {
	
	@OneToOne
	@JoinColumn(name = "order_id")
	@MapsId
	@Id
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "del_ptnr_id")
	private DeliveryPartner delPartner;
	
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private OrderStatus delStatus;
	
	@Column(columnDefinition = "TIMESTAMP")
	LocalDateTime deliveryLog;
	
}
