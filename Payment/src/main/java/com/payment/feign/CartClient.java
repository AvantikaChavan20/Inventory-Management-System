package com.payment.feign;

import com.payment.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CART-SERVICE")
public interface CartClient {

    @GetMapping("/cart/{cartId}")
    CartDto getCartById(@PathVariable("cartId") Long cartId);
}
