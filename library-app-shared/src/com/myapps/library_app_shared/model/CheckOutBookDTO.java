package com.myapps.library_app_shared.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CheckOutBookDTO {
	private String username;
	private String isbn;
	private LocalDate dueDate;
	
	public CheckOutBookDTO() {}
}
