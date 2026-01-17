package com.javaexpress.payment.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.payment.dto.PaymentRequestDTO;
import com.javaexpress.payment.dto.PaymentResponseDTO;
import com.javaexpress.payment.dto.UserDto;
import com.javaexpress.payment.feignclients.UserFeignClient;
import com.javaexpress.payment.models.Payment;
import com.javaexpress.payment.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
		// TODO : Assingment for order communcation
		
		
		UserDto userDto = userFeignClient.findUserId(request.getUserId().intValue());
		
		if(userDto == null) {
			throw new RuntimeException("User Not Found in Db");
		}
		
		Payment payment = new Payment();
		BeanUtils.copyProperties(request, payment);
		payment.setStatus("SUCCESS");
		 
		paymentRepository.save(payment);
		
		
		return mapToDto(payment,userDto);
	}

	private PaymentResponseDTO mapToDto(Payment payment,UserDto userDto) {
		PaymentResponseDTO response = new PaymentResponseDTO();
		BeanUtils.copyProperties(payment, response);
		response.setPaymentId(payment.getId());
		response.setUserDto(userDto);
		return response;
	}
	
	
}
