package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.FoodItem;

public interface FoodItemDao extends JpaRepository<FoodItem, Long> {
	List<FoodItem> findByAvgRatingGreaterThan(double rating);
}
