package com.javaexpress.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CartItemRequestDto;
import com.javaexpress.dto.CartItemResponseDto;
import com.javaexpress.dto.UserDto;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.feignclients.ProductFeignclient;
import com.javaexpress.feignclients.UserFeignclient;
import com.javaexpress.model.CartItem;
import com.javaexpress.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductFeignclient productFeignclient;
	@Autowired
	UserFeignclient userFeignclient;
	
	@Override
	public CartItemResponseDto addToCart(CartItemRequestDto request) {
		// TODO Auto-generated method stub
		UserDto userDto= userFeignclient.findUserId(request.getUserId().intValue());
		
		if(userDto== null) {
			throw new ResourceNotFoundException("User not exist for userId: "+request.getUserId());
		}
		
		if(!productFeignclient.existByProductId(request.getProductId())) {
			throw new ResourceNotFoundException("Product not exist with productId: "+request.getProductId());
		}

		CartItem cartItem = new CartItem();

		BeanUtils.copyProperties(request, cartItem);

		cartRepository.save(cartItem);

		return mapToCartDto(cartItem);
	}

	public CartItemResponseDto mapToCartDto(CartItem cartItem) {
		CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();

		BeanUtils.copyProperties(cartItem, cartItemResponseDto);

		return cartItemResponseDto;
	}

	@Override
	public List<CartItemResponseDto> getUserCart(Long userId) {
		// TODO Auto-generated method stub

		return cartRepository.findByUserId(userId).stream().map(this::mapToCartDto).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void removeItem(Long userId, Long productId) {
		// TODO Auto-generated method stub

		if (cartRepository.existsByUserIdAndProductId(userId, productId)) {
			cartRepository.deleteByUserIdAndProductId(userId, productId);
		} else {
			throw new RuntimeException("No entry exist for thus userid: " + userId);
		}
	}

	@Override
	public CartItemResponseDto updateQuanity(CartItemRequestDto request) {
		// TODO Auto-generated method stub
		CartItem cartItem = cartRepository.findByUserIdAndProductId(request.getUserId(), request.getProductId())
				.orElseThrow(()->new RuntimeException("Item not in the cart"));

		cartItem.setQuantity(request.getQuantity());
		
		cartRepository.save(cartItem);
		return mapToCartDto(cartItem);
	}

	@Override
	@Transactional
	public void clearCart(Long userId) {
		// TODO Auto-generated method stub
		
		cartRepository.deleteByUserId(userId);

	}

}
