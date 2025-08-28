package com.payment.service;

import com.payment.dto.CartDto;
import com.payment.entity.Payment;
import com.payment.exception.PaymentException;
import com.payment.feign.CartClient;
import com.payment.repository.PaymentRepository;
import com.payment.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CartClient cartClient;

    @Override
    public Payment processPayment(Payment payment) {
        CartDto cartDto = cartClient.getCartById(payment.getCartId());

        if (cartDto == null || cartDto.getTotalAmount() == null || cartDto.getUserId() == null) {
            throw new PaymentException("Invalid cart details received from Cart Service");
        }

        payment.setAmount(cartDto.getTotalAmount());
        payment.setUserId(cartDto.getUserId());
        payment.setPaymentStatus("SUCCESS");

        return paymentRepository.save(payment);
    }
}
