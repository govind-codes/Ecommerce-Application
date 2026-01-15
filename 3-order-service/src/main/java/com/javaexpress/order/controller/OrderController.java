package com.javaexpress.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.order.dto.OrderResponseDto;
import com.javaexpress.order.dto.PlaceOrderRequestDto;
import com.javaexpress.order.service.OrderService;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@PostMapping
	OrderResponseDto placeOrder(@RequestBody PlaceOrderRequestDto request) {
		
		return orderService.placeOrder(request);
	}
	@PutMapping("/update-status/{orderId}")
	OrderResponseDto updateOrderStatus(@PathVariable Long orderId,@RequestParam String status) {
		
		return orderService.updateOrderStatus(orderId, status);
	}
	
	@GetMapping("user/{userId}")
	List<OrderResponseDto> getOrdersByUser(@PathVariable Long userId){
		
		return orderService.getOrdersByUser(userId);
		
	}
	@GetMapping("{orderId}")
	OrderResponseDto getOrderById(@PathVariable Long orderId) {
	return orderService.getOrderById(orderId);   
	}
}
