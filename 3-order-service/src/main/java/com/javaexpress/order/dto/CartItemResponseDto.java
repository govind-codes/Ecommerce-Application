package com.javaexpress.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDto{

	private Long id;
	private Long userId;
	private Long productId;
	private Integer quantity;
}