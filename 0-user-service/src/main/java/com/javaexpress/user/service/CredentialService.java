package com.javaexpress.user.service;

import com.javaexpress.user.dto.CredentialDto;

public interface CredentialService {

	public  CredentialDto findByuserName(String username);
}
