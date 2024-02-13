package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.UserEntityDao;
import com.app.dto.CustomerProfileDTO;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	private UserEntityDao userDao;
	
	@Autowired
	private FindAuthenticationDetails userDetails;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CustomerProfileDTO getCustomerProfile() {
		
		return mapper.
				map(userDao.findById(userDetails.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User does not exist."))
						,CustomerProfileDTO.class);
	}

}
