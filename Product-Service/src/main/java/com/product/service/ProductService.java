package com.product.service;

import java.util.List;

import com.product.exception.ProductNotFoundException;
import com.product.model.Product;

public interface ProductService {

	Product createProduct(Product product);
	
	List<Product> getAllProducts();

	Product getProductById(Long id) throws ProductNotFoundException;

	List<Product> getProductsByInventoryId(Long invtId);

	Product updateProduct(Long id, Product product) throws ProductNotFoundException;

	String deleteProduct(Long id) throws ProductNotFoundException;

}
