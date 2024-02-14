package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.FoodItemDao;
import com.app.dto.AddFoodItemDTO;
import com.app.dto.ApiResponse;
import com.app.dto.FoodItemDTO;
import com.app.entities.FoodItem;

@Service
@Transactional
public class FoodItemServiceImpl implements FoodItemService {
	@Autowired
	private FoodItemDao foodDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Value("${fooditem.popRatingBase}")
	private double popRatingBase;
	
	@Autowired
	private ImageHandlingServiceImplFolder imageService;
	
	@Override
	public List<FoodItemDTO> getPopularFoods() {
		List<FoodItem> itemList = foodDao.findByAvgRatingGreaterThan(popRatingBase);
		
		return itemList.stream()
				.map(f -> mapper.map(f, FoodItemDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<FoodItemDTO> getAll() {
		
		return foodDao.findAll()
				.stream()
				.map(f -> mapper.map(f, FoodItemDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addFoodItem(AddFoodItemDTO foodItem) {
		FoodItem item = mapper.map(foodItem, FoodItem.class);
		String imagePath = imageService.uploadImage(foodItem.getImage());
		System.out.println(imagePath);
		item.setImagePath(imagePath);
		foodDao.save(item);
		return new ApiResponse("Item added successfully.");
	}

}
