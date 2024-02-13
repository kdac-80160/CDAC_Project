package com.app.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.app.enums.OrderStatus;
import com.app.enums.PaymentMode;
import com.app.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
public class Order extends BaseEntity implements Serializable {
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userInOrder;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime orderDate;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime orderLog;
	
	private double totalAmount;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private PaymentMode payMode;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private PaymentStatus payStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private OrderStatus orderStatus; // PENDING, ACCEPTED, REJECTED, PREPARING, READY_FOR_DELIVERY
									 // ON_THE_WAY, DELIVERED, CANCELLED
	// This is for restaurant.
	
//	@Enumerated(EnumType.STRING)
//	@Column(length = 20)
//	private OrderStatus delStatus;
	
	@ManyToOne
	@JoinColumn(name = "del_partner_id")
	private UserEntity delInOrder;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderedItem> orderedItemList = new ArrayList<OrderedItem>();
	
	public String getFirstName()
	{
		return userInOrder.getFirstName();
	}
	
	public String getLastName()
	{
		return userInOrder.getLastName();
	}
	
	public String getUserName()
	{
		return userInOrder.getFirstName()+" "+userInOrder.getLastName();
	}
	
	public String getDeliveryGuyName()
	{
		if(delInOrder == null)
			return null;
		return delInOrder.getFirstName()+" "+delInOrder.getLastName();
	}
	
	public String getDeliveryGuyNumber()
	{
		if(delInOrder == null)
			return null;
		return delInOrder.getMobileNo();
	}
}
