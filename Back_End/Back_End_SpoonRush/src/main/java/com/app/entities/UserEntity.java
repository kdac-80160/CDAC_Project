package com.app.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.app.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "secure_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password") // toString excluding password
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class UserEntity extends BaseEntity {
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 30, unique = true, nullable = false)
	private String email;
	@Column(length = 300, nullable = false)
	private String password;
	@Column(length = 13, unique = true)
	private String mobileNo;
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private UserRole role;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime registrationTime;
	
	
	@OneToMany(mappedBy = "userInAddress", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addressList = new ArrayList<Address>();
	@OneToMany(mappedBy = "userInOrder", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orderList = new ArrayList<Order>();
	@OneToMany(mappedBy = "userInCart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> cartList = new ArrayList<CartItem>();
	
	
	public UserEntity(String firstName, String lastName, String email, String password, String mobileNo, UserRole role,
			LocalDateTime registrationTime) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
		this.role = role;
		this.registrationTime = registrationTime;
	}
	
	
	
	
	
}
