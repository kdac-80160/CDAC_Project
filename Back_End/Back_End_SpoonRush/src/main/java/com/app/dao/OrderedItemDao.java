package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.composite_pk.OrderAndFooditemCPK;
import com.app.entities.OrderedItem;

public interface OrderedItemDao extends JpaRepository<OrderedItem, OrderAndFooditemCPK> {

}
