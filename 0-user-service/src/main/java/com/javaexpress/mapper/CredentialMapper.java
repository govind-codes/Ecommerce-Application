package com.javaexpress.mapper;

import org.mapstruct.Mapper;

import com.javaexpress.dto.CredentialDto;
import com.javaexpress.models.Credential;

@Mapper(componentModel = "Spring")
public interface CredentialMapper {

	
	CredentialDto toCredentialDto(Credential credential);
	
	Credential toCredentialEntity(CredentialDto credentialDto);
}
