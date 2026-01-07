package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDto;
import com.example.demo.models.Product;
import com.example.demo.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	

 @Autowired
 ProductService productService;
 
 Logger log= LoggerFactory.getLogger(ProductController.class);
 
 @PostMapping
 @ResponseStatus(code = HttpStatus.CREATED)
 @Operation(description = "Create a new product", summary = "you can create a new product with this api")
 public ProductDto createProduct( @Valid @RequestBody ProductDto productDto) {
     //TODO: process POST request
     log.info("ProductController : : create Product {}",productDto.getProductName());
     return productService.save(productDto);
 }
 
 @GetMapping("{productId}")
 public Product fetchProduct(@PathVariable Long productId) {
	 return productService.findById(productId);
 }
 
 @PutMapping("{productId}")
 public Product updateProduct(@PathVariable Long productId, @RequestBody Product product) {
	 return productService.update(productId, product);
 }
  
 
 @DeleteMapping("{productId}")
 public void deleteProduct(@PathVariable Long productId) {
	 productService.deleteById(productId);
	 
 }
}
