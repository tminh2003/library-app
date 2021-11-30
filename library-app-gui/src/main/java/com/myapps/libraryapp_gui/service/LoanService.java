package com.myapps.libraryapp_gui.service;

import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.LoanDTO;

public class LoanService {

	public LoanDTO[] getAllLoansForUser(String username) {
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.getForObject("http://localhost:8081/loans/" + username, LoanDTO[].class);
	}
}
