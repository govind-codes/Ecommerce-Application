package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaexpress.dto.CredentialDto;
import com.javaexpress.mapper.CredentialMapper;
import com.javaexpress.repository.CredentialRepository;

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
