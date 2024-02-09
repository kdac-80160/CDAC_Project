package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.AddressDao;
import com.app.dao.LocalityDao;
import com.app.dao.UserEntityDao;
import com.app.dto.AddressDTO;
import com.app.dto.ApiResponse;
import com.app.entities.Address;
import com.app.entities.Locality;
import com.app.entities.UserEntity;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private LocalityDao localityDao;
	
	@Autowired
	private UserEntityDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private FindAuthenticationDetails userDetails;
	
	@Override
	public List<AddressDTO> getAddressesForUser() {
		Long userId = userDetails.getUserId();
		return addressDao.findAllByUserInAddressId(2L)
				.stream()
				.map(a -> mapper.map(a, AddressDTO.class))
				.collect(Collectors.toList());
	}
	@Override
	public ApiResponse addAddress(AddressDTO address) {
		Long userId = userDetails.getUserId();
		UserEntity user = userDao.findById(2L).orElse(null);
		Locality locality = localityDao.findById(address.getLocalityId()).orElse(null);
		Address addressEntity = mapper.map(address, Address.class);
		addressEntity.setLocality(locality);
		addressEntity.setUserInAddress(user);
		
		addressEntity = addressDao.save(addressEntity);
		
		return new ApiResponse("Address added with id : "+addressEntity.getId());
	}

}
