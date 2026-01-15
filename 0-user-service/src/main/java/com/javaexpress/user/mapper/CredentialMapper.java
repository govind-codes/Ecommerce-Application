package com.javaexpress.user.mapper;

import org.mapstruct.Mapper;

import com.javaexpress.user.dto.CredentialDto;
import com.javaexpress.user.models.Credential;

@Mapper(componentModel = "spring")
public interface CredentialMapper {

	
	CredentialDto toCredentialDto(Credential credential);
	
	Credential toCredentialEntity(CredentialDto credentialDto);
}
