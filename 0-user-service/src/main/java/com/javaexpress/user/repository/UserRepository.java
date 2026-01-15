package com.javaexpress.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.user.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	
}
