package com.app.enums;

public enum OrderStatus {
	PENDING,//By Default
	CANCELLED, //From User
	ACCEPTED,PREPARING,REJECTED,READY_FOR_DELIVERY, //From Restaurant
	WAITING,ON_THE_WAY,DELIVERED // From delivery boy 
}
