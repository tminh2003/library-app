package com.myapps.libraryapp_db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "my_book_id_generator", initialValue = 0)
public class Book{

	private @Id @GeneratedValue(generator = "my_book_id_generator")  Long id;
	private String title;
	private String author;
	private String isbn;
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
