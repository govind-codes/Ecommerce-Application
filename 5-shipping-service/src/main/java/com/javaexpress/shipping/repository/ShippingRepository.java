package com.javaexpress.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.shipping.models.ShippingInfo;

public interface ShippingRepository extends JpaRepository<ShippingInfo, Long>{

	ShippingInfo findByOrderId(Long orderId);
}
