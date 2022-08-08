package com.demo.demo.model;

import lombok.Data;

@Data
public class User {
 
	private String name;
	private String email;
	private String password;	
	private Long id;
	private boolean status;
	private String role;
}
