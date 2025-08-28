package com.cart.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cart.model.ProductDTO;
@FeignClient(name = "Product-Service")
public interface ProductClient {

	@GetMapping("/products/{productId}")
	ProductDTO getProductById(@PathVariable("productId") Long productId);

}
