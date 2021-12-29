package com.myapps.library_app_shared.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateLoanDTO {
	private String username;
	private String isbn;
	private int howLong;
}
