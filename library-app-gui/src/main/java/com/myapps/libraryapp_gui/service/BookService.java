package com.myapps.libraryapp_gui.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.BookDTO;

public class BookService {

	public BookDTO[] getAllBooks(){
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BookDTO[]> obj= restTemplate.getForEntity(
				"http://localhost:8081/books", BookDTO[].class);
		
		return obj.getBody();
	}
	
	public BookDTO getBookByIdNumber(Long id) {
		BookDTO bookDTO;
		RestTemplate restTemplate = new RestTemplate();
		bookDTO = restTemplate.getForObject(
				"http://localhost:8081/books/" + id, BookDTO.class);
		return bookDTO;
	}
	
}
