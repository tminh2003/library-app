package com.myapps.libraryapp_gui.service;

import java.time.LocalDate;

import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.CheckOutBookDTO;
import com.myapps.library_app_shared.model.ReturnBookDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LibraryService {
	private String RESOURCE_LOCATION;
	
	public void checkOutBookFor(String username, String isbn, int duration) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(	
				RESOURCE_LOCATION + "/checkOut", 
				new CheckOutBookDTO(username, isbn, LocalDate.now().plusDays(duration)), 
				CheckOutBookDTO.class);
	}

	public void returnBookFor(String username, String isbn) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(	
				RESOURCE_LOCATION + "/return", 
				new ReturnBookDTO(username, isbn), 
				ReturnBookDTO.class);
	}
	
	public void recheckBookFor(String username, String isbn, int duration) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(	
				RESOURCE_LOCATION + "/recheck", 
				new ReturnBookDTO(username, isbn), 
				ReturnBookDTO.class);
	}
}
