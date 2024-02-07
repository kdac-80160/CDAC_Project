package com.app.entities;

import javax.persistence.*;

import com.app.enums.TypeOfAddress;

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
	private UserEntity userInAddress;
	
	@Column(length = 10)
	private String houseFlatNo;
	
	@Column(length = 30)
	private String streetName;
	
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

}
