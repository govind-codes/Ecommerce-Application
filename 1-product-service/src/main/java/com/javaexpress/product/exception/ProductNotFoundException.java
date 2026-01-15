package com.javaexpress.product.exception;

public class ProductNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String Message) {
		// TODO Auto-generated constructor stub
		
		super(Message);
	}
}
