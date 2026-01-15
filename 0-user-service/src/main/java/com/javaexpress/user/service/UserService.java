package com.javaexpress.user.service;

import com.javaexpress.user.dto.UserDto;

public interface UserService {

	
	UserDto save(UserDto userDto);
	UserDto updateUser(Integer userId, UserDto userDto);
	UserDto findById(Integer userId);
	void deleteUser(Integer userId);
	
}
