package com.myapps.libraryapp_db.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class User {
	private @Id String username;
	private String email;
	private String password;
	private String authority;
	private double fineBalance;
	
	private User() {}
}
