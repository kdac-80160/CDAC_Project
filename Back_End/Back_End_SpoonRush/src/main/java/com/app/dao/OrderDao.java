package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Order;
import com.app.enums.OrderStatus;

public interface OrderDao extends JpaRepository<Order, Long> {
	
	List<Order> findAllByOrderStatusAndDelInOrderId(OrderStatus orderStatus, Long delUserId);
	
	List<Order> findAllByOrderStatus(OrderStatus orderStatus);
	
	@Modifying
	@Query("update Order o set o.orderStatus = :status where o.id = :orderId and o.userInOrder.id = :userId")
	int changeOrderStatusByUserId(@Param("status") OrderStatus status, @Param("orderId") Long orderId, @Param("userId") Long userId);
	
	@Modifying
	@Query("update Order o set o.orderStatus = :status where o.id = :orderId")
	int changeOrderStatus(@Param("status") OrderStatus status, @Param("orderId") Long orderId);
	
	@Query("select o from Order o where o.orderStatus IN (:statuses)")
	List<Order> findAllByOrderStatuses(@Param("statuses") List<OrderStatus> listStatuses);
	
	@Query("select o from Order o where o.orderStatus IN (:statuses) and o.delInOrder.id = :delId")
	List<Order> findAllByOrderStatusesAndDelPartnerId(@Param("statuses") List<OrderStatus> listStatuses,@Param("delId") Long delPartnerId);
	
	@Query("select o from Order o where o.orderStatus IN (:statuses) and o.delInOrder IS NULL")
	List<Order> findAllByOrderStatusesAndDelPartnerIdIsNull(@Param("statuses") List<OrderStatus> listStatuses);
	
	Optional<Order> findByUserInOrderIdAndId(Long userId, Long orderId);
	
	@Query("select o from Order o where o.orderStatus NOT IN (:statuses) and o.userInOrder.id = :userId")
	List<Order> findAllByExcpetOrderStatusesAndUserId(@Param("statuses") List<OrderStatus> listStatuses, @Param("userId") Long userId);
	
	@Query("select o from Order o where o.orderStatus IN (:statuses) and o.userInOrder.id = :userId")
	List<Order> findAllByOrderStatusesAndUserId(@Param("statuses") List<OrderStatus> listStatuses, @Param("userId") Long userId);
}
