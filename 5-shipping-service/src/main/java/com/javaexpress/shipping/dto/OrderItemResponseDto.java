package com.javaexpress.shipping.dto;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponseDto {

	private Long productId;
	private Integer quantity;
	private BigDecimal price;
}
