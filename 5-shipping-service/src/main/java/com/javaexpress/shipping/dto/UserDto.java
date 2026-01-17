package com.javaexpress.shipping.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data//never use it
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Integer userId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String contact;
}
