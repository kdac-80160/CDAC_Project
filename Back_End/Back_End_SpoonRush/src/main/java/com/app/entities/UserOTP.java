package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_otp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserOTP extends BaseEntity {
	@MapsId
	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@Column(length = 6)
	private String otp;
	
	private boolean verified;
}
