package com.payment.controller;


import com.payment.entity.Payment;
import com.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/checkout")
    public Payment checkout(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }
}
