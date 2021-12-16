package com.myapps.library_app_shared.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanDTO {
	private Long id;
	private String username;
	private String bookIsbn;
	private LocalDate dueDate;
	
	private LoanDTO() {}
	public LoanDTO(String username, String bookIsbn, LocalDate dueDate) {
		this.username = username;
		this.bookIsbn = bookIsbn;
		this.dueDate = dueDate;
	}
}
