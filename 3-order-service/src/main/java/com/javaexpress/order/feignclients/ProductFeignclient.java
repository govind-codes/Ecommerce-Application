package com.javaexpress.order.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.order.dto.ProductResponseDto;

@FeignClient(name = "PRODUCT-SERVICE", path="/api/v1/product")
public interface ProductFeignclient {

	@GetMapping("exist/{id}")
	public boolean existByProductId(@PathVariable("id") Long productId);
	
	@GetMapping("{productId}")
	public ProductResponseDto fetchProduct(@PathVariable Long productId);
}
