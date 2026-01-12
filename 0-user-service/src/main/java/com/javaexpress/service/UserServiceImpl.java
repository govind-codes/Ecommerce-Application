package com.javaexpress.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.UserDto;
import com.javaexpress.exception.UserNotFoundException;
import com.javaexpress.mapper.UserMapper;
import com.javaexpress.models.Credential;
import com.javaexpress.models.User;
import com.javaexpress.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;

	@Override
	public UserDto save(UserDto userDto) {

		User user = userMapper.toUserEntity(userDto);
		System.out.println("credentials: " + userDto.getCredential());
		Credential credential = user.getCredential();

		credential.setUser(user);

		userRepository.save(user);

		userDto = userMapper.toUserDto(user);
		// TODO Auto-generated method stub
		return userDto;
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto userDto) {
		// TODO Auto-generated method stub

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("user details not found for user Id: " + userId));

		userMapper.updateUserFromDto(userDto, user);// this will update the exisitng record , won't create pass new
													// userDto object, just update exisitng and make sure to use ignore
													// id on mappoing annotation in userMapper class
		System.out.println("uerrrrrr2: " + user.getUserId());
		Credential credential = user.getCredential();

		credential.setUser(user);
		userRepository.save(user);

		return userMapper.toUserDto(user);
	}

	@Override
	public UserDto findById(Integer userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).map(userMapper::toUserDto)
				.orElseThrow(() -> new UserNotFoundException("User details not found for this userid : " + userId));
	}
	
	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User details not found for this userid : " + userId));
		userRepository.deleteById(userId);
	}
	

}
