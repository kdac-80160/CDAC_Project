package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.DeliveryLogs;
import com.app.entities.Order;

public interface DeliveryLogsDao extends JpaRepository<DeliveryLogs, Long> {

}
