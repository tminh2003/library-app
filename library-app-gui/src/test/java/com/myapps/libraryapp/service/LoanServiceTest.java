package com.myapps.libraryapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.myapps.library_app_shared.model.LoanDTO;
import com.myapps.libraryapp_gui.service.LoanService;

public class LoanServiceTest {

	@Test
	public void getAllLoansForUser() {
		LoanService loanService = new LoanService("http://localhost:9091/loans");
		
		LoanDTO loanDTO = loanService.getAllLoansFor("jack_frost")[0];
		assertThat(loanDTO.getUsername().equals("jack_frost"));
	}
}
