package com.javaexpress.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.Credential;

public interface CredentialRepository extends JpaRepository<Credential,Integer>{

	
	Optional<Credential> findByusername(String username);
}
