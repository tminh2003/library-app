package com.myapps.libraryapp.service;

import org.junit.jupiter.api.Test;

import com.myapps.libraryapp_gui.service.BookService;

public class BookServiceTest {
	@Test
	public void checkOutBook() {

		BookService bookService = new BookService();
		bookService.checkOutBookForUser(1L, 1L);
	}
}
