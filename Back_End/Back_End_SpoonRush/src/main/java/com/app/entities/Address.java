package com.app.entities;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userInAddress;
	
	@Column(length = 10)
	private String houseFlatNo;
	
	@Column(length = 30)
	private String streetName;
	
	@Column(length = 30)
	private String landmark;
	
	@ManyToOne
	@JoinColumn(name = "locality")
	private Locality locality;
	
	@Column(length = 50)
	private String moreDetails;

}
