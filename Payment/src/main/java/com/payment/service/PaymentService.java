package com.payment.service;

import com.payment.entity.Payment;

public interface PaymentService {
    Payment processPayment(Payment payment);
}
