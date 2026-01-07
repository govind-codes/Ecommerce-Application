package com.example.demo.service;





import com.example.demo.dto.ProductDto;
import com.example.demo.models.Product;

public interface ProductService {

	public ProductDto save(ProductDto productDto);
	public Product update(Long productId, Product product);
	public Product findById(Long productId);
	public void deleteById(Long productId);
}
