package com.myapps.libraryapp_gui.service;

import java.time.LocalDate;

import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.BookDTO;
import com.myapps.library_app_shared.model.LoanDTO;

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

	public void checkOutBookForUser(Long bookId, String username, int howLong) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForObject(	"http://localhost:8081/loans/", 
									new LoanDTO(	username,
													bookId,
													LocalDate.now().plusDays(howLong)),
									LoanDTO.class);
	}
	
	public void returnBookForUser(Long bookId, String username) {
		
	}
	
	public void reportLostBookForUser(Long bookId, String username) {
		
	}
	
}
