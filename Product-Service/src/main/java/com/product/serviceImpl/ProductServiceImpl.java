package com.product.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exception.ProductNotFoundException;
import com.product.model.Product;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) throws ProductNotFoundException{
		// TODO Auto-generated method stub
		Product product=productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found"));
		return product;
	}

	@Override
	public List<Product> getProductsByInventoryId(Long invtId) {
		// TODO Auto-generated method stub
		return productRepository.findByInvtId(invtId);
	}

	@Override
	public Product updateProduct(Long id, Product product) throws ProductNotFoundException{
		// TODO Auto-generated method stub
		Product prod=productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found"));
		prod.setCategory(product.getCategory());
		prod.setInvtId(product.getInvtId());
		prod.setPrice(product.getPrice());
		prod.setProductName(product.getProductName());
		prod.setQuantity(product.getQuantity());
		return productRepository.save(prod);
	}

	@Override
	public String deleteProduct(Long id) throws ProductNotFoundException{
		// TODO Auto-generated method stub
		Product product=productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found"));
		productRepository.delete(product);
		return "Product deleted successfully";
	}

}
