package com.myapps.library_app_shared.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateLoanDTO {
	private String username;
	private String isbn;
	private LocalDate dueDate;
}
