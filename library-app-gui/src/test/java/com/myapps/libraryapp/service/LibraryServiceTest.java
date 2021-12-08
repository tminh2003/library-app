package com.myapps.libraryapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.myapps.libraryapp_gui.service.BookService;
import com.myapps.libraryapp_gui.service.LibraryService;
import com.myapps.libraryapp_gui.service.LoanService;

public class LibraryServiceTest {

	@Test
	public void testRollbackOnFail(){
		//create mock user and book services
		BookService bookService = new BookService();
		LoanService loanService = new LoanService();
		
		String username = "test_user";
		String bookIsbn = "123457";
		
		//make one of them fail to trigger rollback
		
		
		//make sure that the transaction does not finish
		LibraryService libraryService = new LibraryService(bookService, loanService);
		
		boolean everythingIsFine = bookService.getBookByIsbn("").getCurrentStatus().equals("IN");
		everythingIsFine &= loanService.getAllLoansFor(username) == null;
		
		assertThat(everythingIsFine);
	}
}
