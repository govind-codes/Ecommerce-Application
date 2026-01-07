package com.javaexpress.service;

import com.javaexpress.dto.CredentialDto;

public interface CredentialService {

	public  CredentialDto findByuserName(String username);
}
