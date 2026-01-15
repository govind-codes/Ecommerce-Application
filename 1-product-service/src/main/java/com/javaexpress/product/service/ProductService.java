package com.javaexpress.product.service;





import com.javaexpress.product.dto.ProductDto;
import com.javaexpress.product.models.Product;

public interface ProductService {

	public ProductDto save(ProductDto productDto);
	public ProductDto update(Long productId, ProductDto productDto);
	public ProductDto findById(Long productId);
	public void deleteById(Long productId);
	public boolean existByProductId(Long productId);
}
