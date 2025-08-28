package com.product.dto;

import lombok.Data;

@Data
public class ProductDto {
	private Long productId;
	private String product;
	private double price;
}