package com.app.enums;

public enum OrderStatus {
	PENDING,ACCEPTED,PREPARING,WAITING,READY_FOR_DELIVERY,ON_THE_WAY,DELIVERED,CANCELLED,REJECTED
	// For a delivery partner, status with ACCEPTED, PREPARING, READY_FOR_DELIVERY orders are available for delivery.
	// ON_THE_WAY / WAITING / DELIVERED => Will be sent by the delivery boy.
	// 
}
