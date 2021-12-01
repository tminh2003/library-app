package com.myapps.library_app_shared.model;

import lombok.Data;

@Data
public class BookDTO{

	private String title;
	private String author;
	private String isbn;
	private double cost;
	private String currentStatus;
	
	private BookDTO() {}
	
	public BookDTO(String title, String author, String isbn, double cost, String currentStatus) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.cost = cost;
		this.currentStatus = currentStatus;
	}
}