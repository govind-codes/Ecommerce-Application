package com.javaexpress.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.model.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long> {

	//Optional<CartItem> fList<CartItem> findByUserId(Long userId);
	
	List<CartItem> findByUserId(Long userId);
	
	void deleteByUserIdAndProductId(Long userId, Long productId);
	
	void deleteByUserId(Long userId);	 
	Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
	
	Boolean existsByUserIdAndProductId(Long userId, Long productId);
}
