package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.UserDto;
import com.javaexpress.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserDto save(@RequestBody UserDto userDto ) {
		log.info("UserController : : create user {}",userDto.getFirstName());
		return userService.save(userDto);
	}
	
	@GetMapping("{userid}")
	public UserDto find(@PathVariable Integer userid) {
		log.info("UserController : : find user for user id: {}", userid);
		
		return userService.findById(userid);
		
	}
	@PutMapping("{userid}")
	public UserDto update(@PathVariable Integer userid, @RequestBody UserDto userDto) {
	log.info("UserController : : update user for user id: {}", userid);
	
		return userService.updateUser(userid, userDto);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer userid) {
		
		log.info("UserController : : delete user having used id: {}", userid);
		userService.deleteUser(userid);
	}
}
