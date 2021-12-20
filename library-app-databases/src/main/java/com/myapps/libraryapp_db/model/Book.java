package com.myapps.libraryapp_db.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Book{

	private @Id String isbn;
	private String title;
	private String author;
	private double cost;
	private String currentStatus;
	
	private Book() {}
}
