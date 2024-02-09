package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.app.enums.Category;
import com.app.enums.SubCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "food_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "item_id"))
@ToString(exclude = {"cartList"})
public class FoodItem extends BaseEntity {
	
	@Column(length = 30)
	private String itemName;
	
	@Column(length = 200)
	private String imagePath;
	
	@Column(length = 200)
	private String discription;
	
	private double price;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Category category;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private SubCategory subCategory;
	
	public FoodItem(String itemName, String imagePath, String discription, double price, Category category,
			SubCategory subCategory, double avgRating) {
		super();
		this.itemName = itemName;
		this.imagePath = imagePath;
		this.discription = discription;
		this.price = price;
		this.category = category;
		this.subCategory = subCategory;
		this.avgRating = avgRating;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "item")
	private List<CartItem> cartList = new ArrayList<CartItem>();
	
	private double avgRating;
}
