package com.javaexpress.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.user.dto.CredentialDto;
import com.javaexpress.user.service.CredentialService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/credentials")
public class CredentialController {
	
	@Autowired
	CredentialService credentialService;
	
	@GetMapping("exist/{username}")
	public CredentialDto findByusername(@PathVariable String username) {
		log.info("CredentialController : : find the credentials for username: {}", username);
		
		return credentialService.findByuserName(username);
	}

}
