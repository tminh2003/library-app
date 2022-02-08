package com.myapps.libraryapp_gui.service;

import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.BookDTO;

public class BookService{
	private String RESOURCE_LOCATION;
	
	public BookService(String RESOURCE_LOCATION) {
		this.RESOURCE_LOCATION = RESOURCE_LOCATION;
	}

	public BookDTO[] getAllBooks() {
		RestTemplate restTemplate = new RestTemplate();
		BookDTO[] obj = restTemplate.getForObject(RESOURCE_LOCATION, BookDTO[].class);

		return obj;
	}

	public BookDTO getBookByIsbn(String isbn) {
		RestTemplate restTemplate = new RestTemplate();
		BookDTO bookDTO = restTemplate.getForObject(RESOURCE_LOCATION + "/" + isbn, BookDTO.class);
		return bookDTO;
	}
}
