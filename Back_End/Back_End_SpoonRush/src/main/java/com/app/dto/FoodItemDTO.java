package com.app.dto;

import com.app.enums.Category;
import com.app.enums.SubCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {
	private Long id;
	private String itemName;
	private String imagePath;
	private String description;
	private double price;
	private Category category;
	private SubCategory subCategory;
	private double avgRating;
}
