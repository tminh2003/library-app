package com.myapps.libraryapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.myapps.library_app_shared.model.BookDTO;
import com.myapps.libraryapp_gui.service.BookService;

public class BookServiceTest {
	
	@Test
	public void testGetBookByIsb() {
		BookService bookService = new BookService();
		BookDTO bookDTO = bookService.getBookByIsbn("123456");
		assertThat(bookDTO.getTitle().equals("LOTR"));
	}
}
