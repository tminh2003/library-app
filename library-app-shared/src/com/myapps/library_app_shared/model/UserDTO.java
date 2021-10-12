package com.myapps.library_app_shared.model;

import lombok.Data;

@Data
public class UserDTO{
	
	private String username;
	private String email;
	private String password;
	private String authority;

	private UserDTO() {
	}

	public UserDTO(String username, String email, String password, String authority) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.authority = authority;
	}	
}