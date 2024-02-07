package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.composite_pk.UserAndFooditemCPK;
import com.app.entities.CartItem;

public interface CartDao extends JpaRepository<CartItem, UserAndFooditemCPK> {

}
