package com.product.controller;


import com.product.exception.ProductNotFoundException;
import com.product.model.Product;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    	return new ResponseEntity<>(productService.createProduct(product), HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
    	return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException{
    	return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

    @GetMapping("/inventory/{invtId}")
    public ResponseEntity<Product> getProductsByInventoryId(@PathVariable Long invtId) {
        return new ResponseEntity(productService.getProductsByInventoryId(invtId),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotFoundException{
    	return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductNotFoundException{
    	return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
