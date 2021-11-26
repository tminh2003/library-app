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
	private Long bookId;
	private LocalDate dueDate;
	
	private Loan() {}
	
	public Loan(String username, Long bookId, LocalDate dueDate) {
		this.username = username;
		this.bookId = bookId;
		this.dueDate = dueDate;
	}
}

