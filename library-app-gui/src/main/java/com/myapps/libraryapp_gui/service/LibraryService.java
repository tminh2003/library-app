package com.myapps.libraryapp_gui.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LibraryService {
	
	private BookService bookService;
	private LoanService loanService;

	public void checkOutBookForUser(String isbn, String username, int howLong) {
		bookService.seteBookToOut(isbn);
		loanService.createLoanForUser(username, isbn, howLong);
	}

	public void returnBookForUser(Long bookId, String username) {

	}

	public void reportLostBookForUser(Long bookId, String username) {

	}
}
