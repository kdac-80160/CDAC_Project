package com.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.composite_pk.UserAndFooditemCPK;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.CartDao;
import com.app.dto.ApiResponse;
import com.app.dto.CartItemDTO;
import com.app.entities.CartItem;
import com.app.security.CustomUserDetails;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private ModelMapper mapper;
	
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
			cartItem.setAddTime(LocalDateTime.now());
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

	@Override
	public List<CartItemDTO> getUserCartItems() {
		Long userId = userDetails.getUserId();
		return cartDao.findAllByUserInCartId(userId).stream()
				.map(c -> mapper.map(c, CartItemDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse deleteItem(Long itemId) {
		Long userId = userDetails.getUserId();
		UserAndFooditemCPK cpk = new UserAndFooditemCPK(userId, itemId);

		// Check if the user with the given itemId exists in the table or return null
		CartItem cartItem = cartDao.findById(cpk).orElseThrow(()-> new ResourceNotFoundException("Item does not exist."));
		cartDao.delete(cartItem);
		return new ApiResponse("Item deleted successfully.");
	}

}
