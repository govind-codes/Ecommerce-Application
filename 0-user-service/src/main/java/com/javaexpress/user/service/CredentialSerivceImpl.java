package com.javaexpress.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.user.dto.CredentialDto;
import com.javaexpress.user.mapper.CredentialMapper;
import com.javaexpress.user.repository.CredentialRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CredentialSerivceImpl implements CredentialService {


	@Autowired
	private CredentialRepository credentialRepository;

	@Autowired
	private CredentialMapper credentialMapper;

    @Override
	public CredentialDto findByuserName(String username) {
		// TODO Auto-generated method stub

		return credentialRepository.findByusername(username).map(credentialMapper::toCredentialDto)
				.orElseThrow(() -> new RuntimeException("Credentials not found for username: " +username));
    	
	}

}
