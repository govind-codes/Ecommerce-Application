package com.javaexpress.order.service;

import java.util.List;

import com.javaexpress.order.dto.OrderResponseDto;
import com.javaexpress.order.dto.PlaceOrderRequestDto;

public interface OrderService {

	OrderResponseDto placeOrder(PlaceOrderRequestDto request);
	
	OrderResponseDto updateOrderStatus(Long orderId,String status);
	
	List<OrderResponseDto> getOrdersByUser(Long userId);
	
	OrderResponseDto getOrderById(Long orderId);    
	
}