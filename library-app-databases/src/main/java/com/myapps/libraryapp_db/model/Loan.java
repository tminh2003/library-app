package com.myapps.libraryapp_db.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "my_loan_id_generator", initialValue = 0)
public class Loan{

	private @Id @GeneratedValue(generator = "my_loan_id_generator") Long id;
	private String username;
	private String bookIsbn;
	private LocalDate dueDate;
	
	private Loan() {}
	
	public Loan(String username, String bookIsbn, LocalDate dueDate) {
		this.username = username;
		this.bookIsbn = bookIsbn;
		this.dueDate = dueDate;
	}
	
	public Loan(Long id, String username, String bookIsbn, LocalDate dueDate) {
		this.id = id;
		this.username = username;
		this.bookIsbn = bookIsbn;
		this.dueDate = dueDate;
	}
}

