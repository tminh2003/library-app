package com.myapps.libraryapp_gui.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LibraryService {
	
	private BookService bookService;
	private LoanService loanService;

	public void checkOutBookFor(String username, String isbn, int theDuration) {
		bookService.setBookStateTo("OUT", isbn);
		loanService.createLoanFor(username, isbn, theDuration);
	}

	public void returnBookFor(String username, String isbn) {
		bookService.setBookStateTo("IN", isbn);
		loanService.deleteLoanFor(username, isbn);
	}

	public void reportLostBookFor(String username, String isbn) {

	}
}
