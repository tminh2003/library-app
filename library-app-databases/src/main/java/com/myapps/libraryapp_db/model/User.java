package com.myapps.libraryapp_db.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {
	private @Id String username;
	private String email;
	private String password;
	private String authority;
	private double fineBalance;
	private int numCheckedOutBooks;
	
	private User() {}
	
	public User(String username, String email, String password, String authority) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.authority = authority;
		this.fineBalance = 0;
		this.numCheckedOutBooks = 0;
	}
}
