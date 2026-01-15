package com.javaexpress.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.javaexpress.user.dto.UserDto;
import com.javaexpress.user.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(source = "email", target = "emailAddress")
	@Mapping(source = "phone", target = "contact")
	UserDto toUserDto(User user);

	@Mapping(target = "userId", ignore = true)
	@Mapping(target = "email", source = "emailAddress")
	@Mapping(target = "phone", source = "contact")
	User toUserEntity(UserDto userDto);
	
	@Mapping(target = "userId", ignore = true)
	@Mapping(target = "email", source = "emailAddress")
	@Mapping(target = "phone", source = "contact")
	void updateUserFromDto(UserDto dto, @MappingTarget User user);

}
