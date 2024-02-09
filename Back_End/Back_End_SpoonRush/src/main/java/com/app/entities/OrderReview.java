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
@Table(name = "order_reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReview extends BaseEntity {
	
	@JoinColumn(name = "user_id")
	@ManyToOne
	private UserEntity userInReview;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Rating rating;
	
	@Column(columnDefinition = "TEXT")
	private String review;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime timestamp;
}
