package com.app.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.composite_pk.UserAndFooditemCPK;
import com.app.entities.CartItem;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class CartItemDaoTest {

	@Autowired
	private CartDao dao;
	
	
	@Test
	void test() {
		
		List<CartItem> list1 = List.of(new CartItem(new UserAndFooditemCPK(1L, 1L), 3, LocalDateTime.now()),new CartItem(new UserAndFooditemCPK(1L, 2L), 4, LocalDateTime.now()));
		
		List<CartItem> list2 =  dao.saveAll(list1);
		
		assertEquals(list1.size(), list2.size());
	}
	
//	@Test
//	void findByUserIdTest()
//	{
//		List<CartItem> list = dao.findAllByUserInCartId(1L);
//		list.forEach(e->System.out.println(e));
//		
//		assertTrue(list.size()>0);
//	}

}
