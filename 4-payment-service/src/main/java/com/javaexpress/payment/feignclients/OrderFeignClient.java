package com.javaexpress.payment.feignclients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="order-service")
public interface OrderFeignClient {

	
}
