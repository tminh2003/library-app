package com.myapps.libraryapp_db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Book{

	private @Id @GeneratedValue Long id;
	private String title;
	private String author;
	private String isbn;
	private double cost;
	private String currentStatus;
	
	private Book() {}
	
	public Book(String title, String author, String isbn, double cost) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.cost = cost;
		this.currentStatus = "IN";
	}
}
