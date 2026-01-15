package com.javaexpress.order.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.javaexpress.order.dto.CartItemResponseDto;

@FeignClient(name= "CART-SERVICE", path="api/v1/cart")
public interface CartFeignClient {

	@GetMapping("/{userId}")
	public List<CartItemResponseDto> getCartByUserId(@PathVariable Long userId);
	
	@DeleteMapping("/clear/{userId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void clearUserCart(@PathVariable Long userId);
}
