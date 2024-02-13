package com.app.service;

import com.app.dto.CustomerProfileDTO;

public interface UserProfileService {
	CustomerProfileDTO getCustomerProfile();
	CustomerProfileDTO updateCustomerProfile(CustomerProfileDTO profileDTO);
}
