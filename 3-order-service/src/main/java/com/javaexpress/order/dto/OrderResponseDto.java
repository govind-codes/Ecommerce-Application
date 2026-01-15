package com.javaexpress.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.javaexpress.user.dto.UserDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponseDto {

	private Long orderId;
	private Long userId;
	private BigDecimal totalPrice;
	private String status;
	private List<OrderItemResponseDto> items;
	private UserDto userDto;
	private LocalDateTime palcedAt;
}
