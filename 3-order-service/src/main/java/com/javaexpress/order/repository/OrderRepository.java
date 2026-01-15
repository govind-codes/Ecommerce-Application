package com.javaexpress.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	
	List<Order> findByUserId(Long userId);
}
