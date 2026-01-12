package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductMapper productMapper;

	@Override
	public ProductDto save(ProductDto productDto) {
		// TODO Auto-generated method stub
		Product product = new Product();
		
//		product.setName(productDto.getProductName());
//		product.setDescription(productDto.getDescription());
//		product.setPrice(productDto.getProductPrice());
//		product.setStock(productDto.getStock());
		
		product= productMapper.toEntity(productDto);
		
		productRepository.save(product);
		
//		productDto.setCreatedAt(product.getCreatedAt());
//		productDto.setProductName(product.getName());
//		productDto.setDescription(product.getDescription());
//		productDto.setProductPrice(product.getPrice());
//		productDto.setStock(product.getStock());
	
		productDto = productMapper.toDto(product);
		return productDto;
	}

	@Override
	public Product update(Long productId, Product product) {
		// TODO Auto-generated method stub
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
		existingProduct.setName(product.getName());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setStock(product.getStock());
		
		return productRepository.save(existingProduct);
	}

	@Override
	public Product findById(Long productId) {
		// TODO Auto-generated method stub
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
		return existingProduct;
	}

	@Override
	public void deleteById(Long productId) {
		// TODO Auto-generated method stub
		productRepository.findById(productId)
		.orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
		productRepository.deleteById(productId);

	}

	@Override
	public boolean existByProductId(Long productId) {
		// TODO Auto-generated method stub
		
		return productRepository.existsById(productId);
	}

}
