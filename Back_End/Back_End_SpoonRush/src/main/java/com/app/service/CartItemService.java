package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.CartItemDTO;

public interface CartItemService {
	ApiResponse addItem(Long itemId);
	ApiResponse removeItem(Long itemId);
	List<CartItemDTO> getUserCartItems();
	ApiResponse deleteItem(Long itemId);
}
