package com.javaexpress.shipping.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaexpress.shipping.dto.OrderResponseDto;


@FeignClient(name="order-service", path="api/v1/order")
public interface OrderFeignClient {

	@PutMapping("/update-status/{orderId}")
	OrderResponseDto updateOrderStatus(@PathVariable Long orderId,@RequestParam String status);
}
