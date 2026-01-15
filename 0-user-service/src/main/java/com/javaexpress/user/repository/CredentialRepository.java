package com.javaexpress.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.user.models.Credential;

public interface CredentialRepository extends JpaRepository<Credential,Integer>{

	
	Optional<Credential> findByusername(String username);
}
