package com.javaexpress.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.payment.dto.PaymentRequestDTO;
import com.javaexpress.payment.dto.PaymentResponseDTO;
import com.javaexpress.payment.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/payments")
@Slf4j
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public PaymentResponseDTO makePayment(@RequestBody PaymentRequestDTO request) {
		return paymentService.processPayment(request);
	}
}
