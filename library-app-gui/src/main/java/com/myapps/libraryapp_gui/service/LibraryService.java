package com.myapps.libraryapp_gui.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LibraryService {
	
	private BookService bookService;
	private LoanService loanService;

	public void checkOutBookFor(String username, String isbn, int howLong) {
		bookService.seteBookToOut(isbn);
		loanService.createLoanForUser(username, isbn, howLong);
	}

	public void returnBookFor(String username, String isbn) {
	}

	public void reportLostBookFor(String username, String isbn) {

	}
}
