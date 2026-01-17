package com.javaexpress.shipping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.shipping.dto.ShippingRequestDTO;
import com.javaexpress.shipping.dto.ShippingResponseDTO;
import com.javaexpress.shipping.service.ShippingService;

@RestController
@RequestMapping("/api/v1/shipping")
public class ShippingController {

	@Autowired
	private ShippingService shippingService;

	@PostMapping
	public ShippingResponseDTO shipOrder(@RequestBody ShippingRequestDTO request) {
		return shippingService.shipOrder(request);
	}
	
	@PutMapping("/update/{orderId}")
	public ShippingResponseDTO updateShippingStatus(@PathVariable Long orderId,@RequestParam String status) {
		
		return shippingService.updateShippingStatus(orderId,status);
	}
}
