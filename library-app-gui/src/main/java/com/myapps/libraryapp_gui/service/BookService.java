package com.myapps.libraryapp_gui.service;

import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.BookDTO;

public class BookService {

	public BookDTO[] getAllBooks() {
		RestTemplate restTemplate = new RestTemplate();
		BookDTO[] obj = restTemplate.getForObject("http://localhost:8081/books", BookDTO[].class);

		return obj;
	}

	public BookDTO getBookByIsbn(String isbn) {
		RestTemplate restTemplate = new RestTemplate();
		BookDTO bookDTO = restTemplate.getForObject("http://localhost:8081/books/" + isbn, BookDTO.class);
		return bookDTO;
	}

	public void seteBookToOut(String isbn) {
		RestTemplate restTemplate = new RestTemplate();

		BookDTO bookDTO = getBookByIsbn(isbn);
		bookDTO.setCurrentStatus("OUT");
		
		restTemplate.put("http://localhost:8081/books", bookDTO, BookDTO.class);
	}

}
