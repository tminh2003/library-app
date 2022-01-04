package com.myapps.library_app_shared.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DeleteLoanDTO {
	private String username;
	private String isbn;
}
