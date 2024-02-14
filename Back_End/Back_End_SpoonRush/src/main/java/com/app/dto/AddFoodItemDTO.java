package com.app.dto;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.app.enums.Category;
import com.app.enums.SubCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MultipartConfig
@ToString(exclude = "image")
public class AddFoodItemDTO {
	@NotBlank
	private String itemName;
	@NotBlank
	private String description;
	@NotNull
	private double price;
	@NotNull
	private Category category;
	@NotNull
	private SubCategory subCategory;
	@NotNull
	private double avgRating;
	@NotNull
	private MultipartFile image;
}
