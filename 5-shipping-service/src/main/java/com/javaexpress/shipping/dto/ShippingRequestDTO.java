package com.javaexpress.shipping.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShippingRequestDTO {

	private Long orderId;
	private String shippingMethod; // STANARD,EXPRESS
	private String carrier; // FedEx,Shadow,
	
}
