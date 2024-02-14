package com.app.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.FoodItem;
import com.app.enums.Category;
import com.app.enums.SubCategory;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class FoodItemDaoTest {
	@Autowired
	private FoodItemDao dao;
	
	
	@Test
	void test() {
		/*
		new FoodItem("Idli", null, "Filled with joy!", 100, Category.VEG, SubCategory.SOUTH_INDIAN, 4.3),
		new FoodItem("Hakka Noodles", null, "Taste the chinese!", 150, Category.VEG, SubCategory.CHINESE, 4.1),
		new FoodItem("ThumbsUp", null, "Taste the Thunder!", 40, Category.VEG, SubCategory.BEVERAGES, 3.1)
		 */
		List<FoodItem> list = List.of(new FoodItem("Idli", null, "Filled with joy!", 100, Category.VEG, SubCategory.SOUTH_INDIAN, 4.3),
				new FoodItem("Hakka Noodles", null, "Taste the chinese!", 150, Category.VEG, SubCategory.CHINESE, 4.1),
				new FoodItem("ThumbsUp", null, "Taste the Thunder!", 40, Category.VEG, SubCategory.BEVERAGES, 3.1),new FoodItem("Dosa", null, "Taste the delicious India!", 100, Category.VEG, SubCategory.SOUTH_INDIAN, 4.0));
		
		List<FoodItem> list2 = dao.saveAll(list);
		
		assertEquals(list.size(), list2.size());
	}

}
