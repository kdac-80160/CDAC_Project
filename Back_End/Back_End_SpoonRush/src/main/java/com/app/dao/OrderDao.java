package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Order;
import com.app.enums.OrderStatus;

public interface OrderDao extends JpaRepository<Order, Long> {
	List<Order> findAllByOrderStatus(OrderStatus orderStatus);
	@Modifying
	@Query("update Order o set o.orderStatus = :status where o.id = :orderId")
	int changeOrderStatus(@Param("status") OrderStatus status, @Param("orderId") Long orderId);
	@Query("select o from Order o where o.orderStatus IN (:statuses)")
	List<Order> findAllByOrderStatuses(List<OrderStatus> listStatuses);
}
