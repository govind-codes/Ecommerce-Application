package com.javaexpress.shipping.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.shipping.dto.OrderResponseDto;
import com.javaexpress.shipping.dto.ShippingRequestDTO;
import com.javaexpress.shipping.dto.ShippingResponseDTO;
import com.javaexpress.shipping.feignclients.OrderFeignClient;
import com.javaexpress.shipping.models.ShippingInfo;
import com.javaexpress.shipping.repository.ShippingRepository;

@Service
public class ShippingServiceImpl implements ShippingService{

	@Autowired
	private ShippingRepository shippingRepository;
	
	@Autowired
	OrderFeignClient orderFeignClient;
	
	@Override
	public ShippingResponseDTO shipOrder(ShippingRequestDTO request) {
		
		// TODO : Assingment for order communcation
		ShippingInfo shippingInfo = new ShippingInfo();
		BeanUtils.copyProperties(request, shippingInfo);
		shippingInfo.setStatus("SHIPPED");
		shippingInfo.setShippedAt(LocalDateTime.now());
		
		ShippingInfo dbShipping = shippingRepository.save(shippingInfo);
		
		// TODO: update the order status to SHIPPED
		
		return mapToDto(dbShipping);
	}

	private ShippingResponseDTO mapToDto(ShippingInfo dbShipping) {
		ShippingResponseDTO response = new ShippingResponseDTO();
		BeanUtils.copyProperties(dbShipping, response);
		response.setShippedAt(response.getShippedAt());
		return response;
	}

	@Override
	public ShippingResponseDTO updateShippingStatus(Long orderId, String status) {
		
		ShippingInfo shippingInfo = shippingRepository.findByOrderId(orderId);
		if(shippingInfo == null) {
			throw new RuntimeException("Order Not Found");
		}
		
		shippingInfo.setStatus(status);
		shippingInfo.setDeliveryDate("DELIVERED".equals(status) ? LocalDateTime.now(): null);
		
		shippingRepository.save(shippingInfo);
		ShippingResponseDTO response = new ShippingResponseDTO();
		BeanUtils.copyProperties(shippingInfo, response);
		
		if("DELIVERED".equals(status)) {
			// update the order status to SHIPPED to DELIVERED
			OrderResponseDto orderResponseDto= orderFeignClient.updateOrderStatus(orderId, status);
		}
		return response;
		
	}

	
	
}
