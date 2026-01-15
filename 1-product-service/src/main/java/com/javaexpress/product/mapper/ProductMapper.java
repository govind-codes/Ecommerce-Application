package com.javaexpress.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.javaexpress.product.dto.ProductDto;
import com.javaexpress.product.models.Product;


@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	
	@Mapping(target = "productName", source = "name")
	@Mapping(target = "productPrice", source = "price")
	ProductDto toDto(Product product);
	
	@Mapping(source = "productName", target = "name")
	@Mapping(source = "productPrice", target = "price")
	Product toEntity(ProductDto productDto);	

	
}
