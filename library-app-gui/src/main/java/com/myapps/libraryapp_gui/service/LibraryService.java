package com.myapps.libraryapp_gui.service;

import com.myapps.library_app_shared.model.LoanDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LibraryService {
	
	private UserService userService;
	private BookService bookService;
	private LoanService loanService;

	public void checkOutBookFor(String username, String isbn, int duration) {
		loanService.createLoanFor(username, isbn, duration);
		bookService.setBookStateTo("OUT", isbn);
		userService.chargeUser(username, bookService.getBookByIsbn(isbn).getCost());
	}

	public void returnBookFor(String username, String isbn) {
		bookService.setBookStateTo("IN", isbn);
		loanService.deleteLoanFor(username, isbn);
	}
	
	public void recheckBookFor(String username, String isbn, int duration) {
		LoanDTO[] allLoans = loanService.getAllLoansFor(username);
		for(LoanDTO loanDTO : allLoans) {
			if(loanDTO.getBookIsbn().equals(isbn)) {
				loanService.updateLoanFor(loanDTO.getId(), username, isbn, duration);
			}
		}
		userService.chargeUser(username, bookService.getBookByIsbn(isbn).getCost());
	}
}
