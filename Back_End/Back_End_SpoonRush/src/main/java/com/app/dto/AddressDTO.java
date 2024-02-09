package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.app.entities.Locality;
import com.app.enums.TypeOfAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	@NotBlank
	private String houseFlatNo;
	@NotBlank
	private String streetName;
	@NotNull
	private TypeOfAddress type;
	private String landmark;
	@JsonProperty(access = Access.READ_ONLY)
	private Locality locality;
	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long localityId;
	private String moreDetails;
}
