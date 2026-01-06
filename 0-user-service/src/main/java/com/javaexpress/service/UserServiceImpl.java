package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.UserDto;
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
		System.out.println("credentials: "+userDto.getCredential());
		Credential credential= user.getCredential();
		
		System.out.println("credentials: "+credential.getUsername()+"' "+ credential.getPassword()+","+credential.getRoleBasedAuthority());
		credential.setUser(user);
		
		userRepository.save(user);
		
		userDto = userMapper.toUserDto(user);
		// TODO Auto-generated method stub
		return userDto;
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto findById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
