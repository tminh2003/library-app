package com.myapps.library_app_shared.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO{

	private String title;
	private String author;
	private String isbn;
	private double cost;
	private String currentStatus;
	
	private BookDTO() {}
}