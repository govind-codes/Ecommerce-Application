package com.javaexpress.exception;

public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String Message) {
		// TODO Auto-generated constructor stub
		
		super(Message);
	}
}
