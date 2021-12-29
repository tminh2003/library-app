package com.myapps.libraryapp_db.test.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.myapps.library_app_shared.model.CreateLoanDTO;
import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.repository.LoanRepository;
import com.myapps.libraryapp_db.service.LoanService;

@SpringBootTest()
public class LoanServiceTest {
	private LoanRepository loanRepository;
	
	@Test
	public void testGetAllLoans() {
		LoanRepository loanRepository = mock(LoanRepository.class);
		List<Loan> expectedList = new ArrayList<Loan>();
		expectedList.add(new Loan(1L, "test_user", "123456", LocalDate.now()));
		
		when(loanRepository.findAll()).thenReturn(expectedList);
		
		LoanService loanService = new LoanService(loanRepository);
		List<Loan> retrievedList = loanService.getAllLoans();
		
		assert(retrievedList.equals(expectedList));
	}

	@Test
	public void testGetAllLoansForUser() {
		LoanRepository loanRepository = mock(LoanRepository.class);
		List<Loan> allLoans = new ArrayList<Loan>();
		allLoans.add(new Loan(1L, "test_user1", "123456", LocalDate.now()));
		allLoans.add(new Loan(2L, "test_user1", "123457", LocalDate.now()));
		allLoans.add(new Loan(3L, "test_user2", "123458", LocalDate.now()));
		allLoans.add(new Loan(4L, "test_user2", "123459", LocalDate.now()));
		
		List<Loan> user1Loans = new ArrayList<Loan>();
		user1Loans.add(new Loan(1L, "test_user1", "123456", LocalDate.now()));
		user1Loans.add(new Loan(2L, "test_user1", "123457", LocalDate.now()));

		List<Loan> user2Loans = new ArrayList<Loan>();
		user1Loans.add(new Loan(3L, "test_user2", "123458", LocalDate.now()));
		user1Loans.add(new Loan(4L, "test_user2", "123459", LocalDate.now()));
		
		when(loanRepository.findByUsername("test_user1")).thenReturn(user1Loans);
		when(loanRepository.findByUsername("test_user2")).thenReturn(user2Loans);
		
		boolean retrievedLoansOfCorrectUser;
		LoanService loanService = new LoanService(loanRepository);
		List<Loan> retrievedList = loanService.getAllLoansFor("test_user1");
		retrievedLoansOfCorrectUser = retrievedList.equals(user1Loans);
		
		retrievedList = loanService.getAllLoansFor("test_user2");
		retrievedLoansOfCorrectUser &= retrievedList.equals(user2Loans); 
		assert(retrievedLoansOfCorrectUser);
	}

	@Test
	public void testCreateLoan(ApplicationContext appContext) {
		LoanRepository loanRepository = appContext.getBean(LoanRepository.class);
		LoanService loanService = new LoanService(loanRepository);
		
		String username = "test_user1";
		String isbn = "123456";
		int howLong = 30;
		LocalDate dueDate = LocalDate.now().plusDays(howLong);
		
		CreateLoanDTO createLoanDTO = new CreateLoanDTO(username, isbn, howLong);
		loanService.createLoan(createLoanDTO);
		
		Loan retrievedLoan = loanRepository.findByUsername(username).get(0);
		
		assert(	retrievedLoan.getUsername().equals(username) && 
				retrievedLoan.getBookIsbn().equals(isbn) && 
				retrievedLoan.getDueDate().equals(dueDate));
	}

	public void testUpdateLoan() {

	}

	public void testDeleteLoan() {

	}
}
