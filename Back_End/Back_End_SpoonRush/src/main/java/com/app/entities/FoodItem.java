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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "food_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "item_id"))
public class FoodItem {
	
	@Column(length = 30)
	private String itemName;
	
	@Column(length = 100)
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
	
	@OneToMany(mappedBy = "item")
	private List<CartItem> cartList = new ArrayList<CartItem>();
	
	private int avgRating;
}
