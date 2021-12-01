package com.myapps.libraryapp_db.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Book{

	private @Id String isbn;
	private String title;
	private String author;
	private double cost;
	private String currentStatus;
	
	public Book() {}
	
	public Book(String title, String author, String isbn, double cost, String currentStatus) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.cost = cost;
		this.currentStatus = currentStatus;
	}
}
