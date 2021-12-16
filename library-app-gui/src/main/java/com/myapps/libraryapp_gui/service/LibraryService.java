package com.myapps.libraryapp_gui.service;

import java.time.LocalDate;

import com.myapps.library_app_shared.model.LoanDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LibraryService {
	
	private BookService bookService;
	private LoanService loanService;

	public void checkOutBookFor(String username, String isbn, int duration) {
		bookService.setBookStateTo("OUT", isbn);
		loanService.createLoanFor(username, isbn, duration);
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
	}

	public void reportLostBookFor(String username, String isbn) {

	}
}
