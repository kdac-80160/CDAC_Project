package com.app.service;

import com.app.dto.ApiResponse;

public interface CartItemService {
	ApiResponse addItem(Long itemId);
	ApiResponse removeItem(Long itemId);
}
