package com.myapps.libraryapp_gui.service;

import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.BookDTO;
import com.myapps.library_app_shared.model.UserDTO;

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

	public void checkOutBookForUser(Long bookId, UserDTO user) {
		
	}
	
	public void returnBookForUser(Long bookId, UserDTO user) {
		
	}
	
	public void reportLostBookForUser(Long bookId, UserDTO user) {
		
	}
	
}
