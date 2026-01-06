package com.javaexpress.dto;

import com.javaexpress.models.RoleBasedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CredentialDto {

	
	private String username;
	private String password;
	
	private RoleBasedAuthority roleBasedAuthority;
	
}
