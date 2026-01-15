package com.javaexpress.order.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseDto {

	private Long id;
	private String productName;
	private String description;
	private BigDecimal productPrice;
	private Integer stock;
}