package com.myapps.libraryapp_gui.service;

import java.time.LocalDate;

import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.CreateLoanDTO;
import com.myapps.library_app_shared.model.LoanDTO;

public class LoanService {
	private String RESOURCE_LOCATION;
	
	public LoanService(String RESOURCE_LOCATION) {
		this.RESOURCE_LOCATION = RESOURCE_LOCATION;
	}

	public LoanDTO[] getAllLoansFor(String username) {
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.getForObject(RESOURCE_LOCATION + "/" + username, LoanDTO[].class);
	}
	//----------------------------------------------------------------------------------------------
	public void createLoanFor(String username, String bookIsbn, int howLong) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForObject(RESOURCE_LOCATION,
				new CreateLoanDTO(username, bookIsbn, LocalDate.now().plusDays(howLong)), CreateLoanDTO.class);
	}
	
	public void updateLoanFor(Long id, String username, String bookIsbn, int howLong) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.put(	RESOURCE_LOCATION, 
							new LoanDTO(id, username, bookIsbn, LocalDate.now().plusDays(howLong)), 
							LoanDTO.class);
	}
	public void deleteLoanFor(String username, String isbn) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete(	RESOURCE_LOCATION
							+	"/"
							+	"?username=" + username
							+	"&bookIsbn=" + isbn);
	}
}
