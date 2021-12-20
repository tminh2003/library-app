package com.myapps.libraryapp_db.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
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
}

