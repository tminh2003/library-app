package com.myapps.library_app_shared.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanDTO {
	private String username;
	private Long bookId;
	private LocalDate dueDate;
	
	private LoanDTO() {}
}
