package com.javaexpress.order.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.user.dto.UserDto;

@FeignClient(name= "USER-SERVICE", path="api/v1/user")
public interface UserFeignclient {
	
	@GetMapping("{userid}")
	public UserDto findUserId(@PathVariable Integer userid);
}
