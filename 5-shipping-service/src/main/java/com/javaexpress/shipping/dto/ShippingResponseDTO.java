package com.javaexpress.shipping.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShippingResponseDTO {

	private Long orderId;
	private String shippingMethod;
	private String status;
	private String carrier;
	private LocalDateTime shippedAt;
	private LocalDateTime deliveryDate;
}
