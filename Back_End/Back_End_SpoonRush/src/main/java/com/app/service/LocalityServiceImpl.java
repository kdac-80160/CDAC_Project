package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.LocalityDao;
import com.app.dto.LocalityDTO;

@Service
@Transactional
public class LocalityServiceImpl implements LocalityService {
	@Autowired
	private LocalityDao localityDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<LocalityDTO> getLocalities() {
		
		return localityDao.findAll()
				.stream()
				.map(l -> mapper.map(l, LocalityDTO.class))
				.collect(Collectors.toList());
	}

}
