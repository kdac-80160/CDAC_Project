package com.app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.composite_pk.UserAndFooditemCPK;
import com.app.dao.CartDao;
import com.app.dto.ApiResponse;
import com.app.entities.CartItem;
import com.app.security.CustomUserDetails;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private FindAuthenticationDetails userDetails;

	@Override
	public ApiResponse addItem(Long itemId) {
		Long userId = userDetails.getUserId();
		UserAndFooditemCPK cpk = new UserAndFooditemCPK(userId, itemId);
		// Check if the user with the given itemId exists in the table or return null
		CartItem cartItem = cartDao.findById(cpk).orElse(null);
		if (cartItem != null) {
			// if exists then increase the count
			cartItem.setQuantity(cartItem.getQuantity() + 1);
			return new ApiResponse("Updated item " + itemId + " with quantity " + cartItem.getQuantity());
		} else {
			// If row does not exists then insert a new row with quantity as one
			CartItem newItem = new CartItem(cpk, 1, LocalDateTime.now());
			cartDao.save(newItem);
			return new ApiResponse("Added item " + itemId + " with quantity 1");
		}
	}

	@Override
	public ApiResponse removeItem(Long itemId) {
		Long userId = userDetails.getUserId();
		UserAndFooditemCPK cpk = new UserAndFooditemCPK(userId, itemId);

		// Check if the user with the given itemId exists in the table or return null
		CartItem cartItem = cartDao.findById(cpk).orElse(null);
		if (cartItem != null) {
			// if exists then check the count
			if(cartItem.getQuantity() == 1)
			{
				// if quantity is 1 then further it will become zero, so mark the row for deletion
				cartDao.delete(cartItem);
				return new ApiResponse("Item removed from cart.");
			}
			// else decrease it's quantity
			cartItem.setQuantity(cartItem.getQuantity() - 1);
			return new ApiResponse("Updated item " + itemId + " with quantity " + cartItem.getQuantity());
		} else {
			// If row does not exists then give back response as no such item in the cart
			return new ApiResponse("No such item is there.");
		}
	}

}
