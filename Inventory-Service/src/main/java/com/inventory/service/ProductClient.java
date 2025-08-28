package com.inventory.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inventory.model.Product;

@FeignClient("Product-Service")
public interface ProductClient {
	@GetMapping("products/inventory/{invtId}")
    public List<Product> getProductsByInventoryId(@PathVariable Long invtId);
}
