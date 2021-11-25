package com.myapps.libraryapp_gui.service;

import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.BookDTO;

public class BookService {

	public BookDTO[] getAllBooks(){
		RestTemplate restTemplate = new RestTemplate();
		BookDTO[] obj= restTemplate.getForObject(
				"http://localhost:8081/books", BookDTO[].class);
		
		return obj;
	}
	
	public BookDTO getBookByIdNumber(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		BookDTO bookDTO = restTemplate.getForObject(
				"http://localhost:8081/books/" + id, BookDTO.class);
		return bookDTO;
	}

	public void checkOutBookForUser(Long bookId, Long userId) {
		
	}
	
	public void returnBookForUser(Long bookId, Long userId) {
		
	}
	
	public void reportLostBookForUser(Long bookId, Long userId) {
		
	}
	
}
