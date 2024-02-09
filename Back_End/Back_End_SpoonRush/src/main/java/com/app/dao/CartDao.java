package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.composite_pk.UserAndFooditemCPK;
import com.app.entities.CartItem;

public interface CartDao extends JpaRepository<CartItem, UserAndFooditemCPK> {
	List<CartItem> findAllByUserInCartId(Long userId);
	Long deleteAllByUserInCartId(Long userId);
//	@Query("update CartItem c SET c.quantity = c.quantity + 1 where p.userInCart.id = :userId AND p.item.id = :itemId")
//	Long increaseQuantity(@Param("userId")Long userId, @Param("itemId")Long itemId);
}
