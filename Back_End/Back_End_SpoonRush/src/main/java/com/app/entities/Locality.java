package com.app.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Table(name = "localities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "locality_id"))
public class Locality extends BaseEntity {
	@Column(length = 30)
	private String localityName;
	@Column(length = 6)
	private int pincode;
	@Column(length = 30)
	@JsonIgnore
	private String city;
	@JsonIgnore
	@Column(length = 30)
	private String state;
}
