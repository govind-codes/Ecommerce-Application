package com.javaexpress.shipping.service;

import com.javaexpress.shipping.dto.ShippingRequestDTO;
import com.javaexpress.shipping.dto.ShippingResponseDTO;

public interface ShippingService {

	ShippingResponseDTO shipOrder(ShippingRequestDTO request);
	
	ShippingResponseDTO updateShippingStatus(Long orderId,String status);
}
