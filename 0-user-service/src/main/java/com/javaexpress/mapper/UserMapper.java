package com.javaexpress.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.javaexpress.dto.UserDto;
import com.javaexpress.models.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {

	@Mapping(source = "email", target = "emailAddress")
	@Mapping(source = "phone", target = "contact")
	UserDto toUserDto(User user);

	@Mapping(target = "email", source = "emailAddress")
	@Mapping(target = "phone", source = "contact")
	User toUserEntity(UserDto userDto);

}
