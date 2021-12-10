package com.myapps.libraryapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.myapps.libraryapp_gui.service.BookService;
import com.myapps.libraryapp_gui.service.LibraryService;
import com.myapps.libraryapp_gui.service.LoanService;

public class LibraryServiceTest {

	@Test
	public void testRollbackOnFail() {
		// create mock user and book services
		BookService mockBookService = mock(BookService.class);
		BookService realBookService = new BookService("http://localhost:9091/books");
		LoanService loanService = new LoanService("http://localhost:9091/loans");

		String username = "test_user";
		String bookIsbn = "123457";

		// make one of them fail to trigger rollback
		doThrow(new RuntimeException()).when(mockBookService).setBookStateTo("OUT", bookIsbn);

		// make sure that the transaction did not finish
		LibraryService libraryService = new LibraryService(mockBookService, loanService);

		try {
			libraryService.checkOutBookFor(username, bookIsbn, 30);
		} catch (Exception e) {}
		
		boolean everythingIsFine = realBookService.getBookByIsbn(bookIsbn).getCurrentStatus().equals("IN");
		everythingIsFine &= loanService.getAllLoansFor(username) == null;

		assertThat(everythingIsFine);
	}
}
