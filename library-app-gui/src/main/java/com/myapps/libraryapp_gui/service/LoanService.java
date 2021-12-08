package com.myapps.libraryapp_gui.service;

import java.time.LocalDate;

import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.LoanDTO;

public class LoanService {

	public LoanDTO[] getAllLoansFor(String username) {
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.getForObject("http://localhost:8081/loans/" + username, LoanDTO[].class);
	}
	//----------------------------------------------------------------------------------------------
	public void createLoanFor(String username, String bookIsbn, int howLong) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForObject("http://localhost:8081/loans",
				new LoanDTO(username, bookIsbn, LocalDate.now().plusDays(howLong)), LoanDTO.class);
	}
	public void deleteLoanFor(String username, String isbn) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete(	"http://localhost:8081/loans"
							+	"?username=" + username
							+	"&bookIsbn=" + isbn);
	}
}
