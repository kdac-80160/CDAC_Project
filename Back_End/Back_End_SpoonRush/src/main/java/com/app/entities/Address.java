package com.app.entities;

import javax.persistence.*;

import com.app.enums.TypeOfAddress;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "address_id"))
public class Address extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private UserEntity userInAddress;
	
	@Column(length = 10)
	private String houseFlatNo;
	
	@Column(length = 30)
	private String streetName;
	
	@Column(length = 13, unique = true)
	private String mobileNo;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private TypeOfAddress type;
	
	@Column(length = 30)
	private String landmark;
	
	@ManyToOne
	@JoinColumn(name = "locality")
	private Locality locality;
	
	@Column(length = 50)
	private String moreDetails;

	public Address(String houseFlatNo, String streetName, TypeOfAddress type, String landmark, String moreDetails) {
		super();
		this.houseFlatNo = houseFlatNo;
		this.streetName = streetName;
		this.type = type;
		this.landmark = landmark;
		this.moreDetails = moreDetails;
	}
	
	

}
