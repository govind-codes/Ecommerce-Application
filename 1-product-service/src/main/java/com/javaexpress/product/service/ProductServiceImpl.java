package com.javaexpress.product.service;

import java.beans.beancontext.BeanContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.stereotype.Service;

import com.javaexpress.product.dto.ProductDto;
import com.javaexpress.product.exception.ProductNotFoundException;
import com.javaexpress.product.mapper.ProductMapper;
import com.javaexpress.product.models.Product;
import com.javaexpress.product.repository.ProductRepository;


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
		
		product.setName(productDto.getProductName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getProductPrice());
		product.setStock(productDto.getStock());
		
//		product= productMapper.toEntity(productDto);
		
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
	public ProductDto update(Long productId, ProductDto productDto) {
		// TODO Auto-generated method stub
		
		
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
		existingProduct.setName(productDto.getProductName());
		existingProduct.setDescription(productDto.getDescription());
		existingProduct.setPrice(productDto.getProductPrice());
		existingProduct.setStock(productDto.getStock());
		
		Product productResponse=   productRepository.save(existingProduct);
		return productMapper.toDto(productResponse);
	}

	@Override
	public ProductDto findById(Long productId) {
		// TODO Auto-generated method stub
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
		return productMapper.toDto(existingProduct);
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
