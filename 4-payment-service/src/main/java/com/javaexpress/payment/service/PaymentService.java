package com.javaexpress.payment.service;

import com.javaexpress.payment.dto.PaymentRequestDTO;
import com.javaexpress.payment.dto.PaymentResponseDTO;

public interface PaymentService {

	PaymentResponseDTO processPayment(PaymentRequestDTO request);
}
