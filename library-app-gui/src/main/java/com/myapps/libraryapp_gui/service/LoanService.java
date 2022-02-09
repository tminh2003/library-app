package com.myapps.libraryapp_gui.service;

import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.LoanDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoanService {
	private String RESOURCE_LOCATION;
	
	public LoanDTO[] getAllLoansFor(String username) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(RESOURCE_LOCATION + "/" + username, LoanDTO[].class);	
	}
}
