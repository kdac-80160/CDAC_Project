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

import com.app.enums.PaymentMode;
import com.app.enums.PaymentStatus;

import lombok.*;

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity {
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private PaymentMode paymentMode;
	
	@Column(length = 100)
	private String transactionId;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime paymentTime;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	private double amountPaid;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private PaymentStatus status;
}
