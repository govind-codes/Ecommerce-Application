package com.javaexpress.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
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
	private LocalDateTime createdAt;
	
	
}
