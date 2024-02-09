package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.app.enums.Rating;

import lombok.*;

@Entity
@Table(name = "del_partner_reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPartnerReview extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "order_id")
	@MapsId
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userInDPR;
	
	@ManyToOne
	@JoinColumn(name = "del_partner_id")
	private UserEntity delInDPR;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Rating rating;
	
	@Column(columnDefinition = "TEXT")
	private String review;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime timestamp;
	
}
