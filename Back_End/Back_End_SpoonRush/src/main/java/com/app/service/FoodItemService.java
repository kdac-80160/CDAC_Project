package com.app.service;

import java.util.List;

import com.app.dto.AddFoodItemDTO;
import com.app.dto.ApiResponse;
import com.app.dto.FoodItemDTO;

public interface FoodItemService {
	List<FoodItemDTO> getPopularFoods();
	List<FoodItemDTO> getAll();
	ApiResponse addFoodItem(AddFoodItemDTO foodItem);
}
