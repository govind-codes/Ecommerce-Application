package com.javaexpress.cart.service;

import java.util.List;

import com.javaexpress.cart.dto.CartItemRequestDto;
import com.javaexpress.cart.dto.CartItemResponseDto;

public interface CartService {
	
	
	CartItemResponseDto addToCart(CartItemRequestDto request);
	
	List<CartItemResponseDto> getUserCart(Long userId);
	
	void removeItem(Long userId,Long productId);
	
	CartItemResponseDto updateQuanity(CartItemRequestDto request);
	
	void clearCart(Long userId);
}
