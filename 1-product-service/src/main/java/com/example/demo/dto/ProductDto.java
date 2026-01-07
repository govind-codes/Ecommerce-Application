package com.example.demo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Size(min= 3, max=20, message = "product name must be between 3 to 20 characters")
	private String productName;
	@NotBlank(message = "Product description must not be blank")
	private String description;
	
	@Digits(integer = 10, fraction = 2)
	@NotNull
	private BigDecimal productPrice;
	
	@NotNull
	@Min(value= 1, message = "stock must be minimum 1")
	private Integer stock;
	private LocalDateTime createdAt = LocalDateTime.now();
	
	
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDto(String productName, String description, BigDecimal productPrice, Integer stock,
			LocalDateTime createdAt) {
		super();
		this.productName = productName;
		this.description = description;
		this.productPrice = productPrice;
		this.stock = stock;
		this.createdAt = createdAt;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "productDto [productName=" + productName + ", description=" + description + ", productPrice="
				+ productPrice + ", stock=" + stock + ", createdAt=" + createdAt + "]";
	}
	
}
