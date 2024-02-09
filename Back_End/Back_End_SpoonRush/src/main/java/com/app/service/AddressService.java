package com.app.service;

import java.util.List;

import com.app.dto.AddressDTO;
import com.app.dto.ApiResponse;

public interface AddressService {
	List<AddressDTO> getAddressesForUser();
	ApiResponse addAddress(AddressDTO address);
}
