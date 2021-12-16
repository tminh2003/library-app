package com.myapps.libraryapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.myapps.library_app_shared.model.LoanDTO;
import com.myapps.libraryapp_gui.service.LoanService;

public class LoanServiceTest {
	private LoanService loanService;

	@BeforeEach
	public void setUp() {
		loanService = new LoanService("http://localhost:8081/loans");
	}
	
	@Test
	public void getAllLoansForUser() {
		LoanDTO loanDTO = loanService.getAllLoansFor("jack_frost")[0];
		assertThat(loanDTO.getUsername().equals("jack_frost"));
	}
	
	@Test
	public void updateLoanTest() {
		int rentDuration = 60;
		
		loanService.updateLoanFor(0L, "jack_frost", "123456", rentDuration);
		
		LoanDTO[] allLoans = loanService.getAllLoansFor("jack_frost");
		
		boolean everythingIsFine = allLoans.length == 1;
		everythingIsFine &= allLoans[0].getId() == 0;
		everythingIsFine &= allLoans[0].getDueDate().equals(LocalDate.now().plusDays(rentDuration));
		assertThat(everythingIsFine);
	}
}
