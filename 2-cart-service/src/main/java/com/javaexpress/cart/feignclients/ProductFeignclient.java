package com.javaexpress.cart.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE", path="/api/v1/product")
public interface ProductFeignclient {

	@GetMapping("exist/{id}")
	public boolean existByProductId(@PathVariable("id") Long productId);
}
