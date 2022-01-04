package com.myapps.libraryapp_db.test.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

import com.myapps.library_app_shared.model.CreateLoanDTO;
import com.myapps.library_app_shared.model.UpdateLoanDTO;
import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.repository.LoanRepository;
import com.myapps.libraryapp_db.service.LoanService;

@SpringBootTest
public class LoanServiceTest {
	private LoanRepository loanRepository;
	
	@Autowired
	private ApplicationContext appContext;
	
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
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void testCreateLoan() {
		LoanRepository loanRepository = appContext.getBean(LoanRepository.class);
		LoanService loanService = new LoanService(loanRepository);
		
		String username = "test_user1";
		String isbn = "123456";
		int howLong = 30;
		LocalDate dueDate = LocalDate.now().plusDays(howLong);
		
		CreateLoanDTO createLoanDTO = new CreateLoanDTO(username, isbn, howLong);
		loanService.createLoan(createLoanDTO);
		
		Loan retrievedLoan = loanRepository.findByUsername(username).get(0);
		
		boolean everythingIsOk = retrievedLoan.getUsername().equals(username);
		everythingIsOk &= retrievedLoan.getBookIsbn().equals(isbn);
		everythingIsOk &= retrievedLoan.getDueDate().equals(dueDate);
		
		assert(everythingIsOk);
	}

	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void testUpdateLoan() {
		LoanRepository loanRepository = appContext.getBean(LoanRepository.class);
		LoanService loanService = new LoanService(loanRepository);
		
		Long loanId = 1L;
		String usernameBefore = "test_user1";
		String isbnBefore = "123457";
		int howLongBefore = 30;
		LocalDate dueDateBefore = LocalDate.now().plusDays(howLongBefore);
		
		String usernameAfter = "test_user1";
		String isbnAfter = "123457";
		int howLongAfter = 60;
		LocalDate dueDateAfter = LocalDate.now().plusDays(howLongAfter);

		loanRepository.save(new Loan(usernameBefore, isbnBefore, dueDateBefore));
		loanService.updateLoan(new UpdateLoanDTO(1L , usernameAfter, isbnAfter, howLongAfter));


		Loan expectedLoan = new Loan(loanId, usernameAfter, isbnAfter, dueDateAfter);
		Loan retrievedLoan = loanRepository.findByUsername(usernameAfter).get(0);
		
		System.out.println(expectedLoan.toString());
		System.out.println(retrievedLoan.toString());
				
		assert(retrievedLoan.equals(expectedLoan));
	}

	@Test
	public void testDeleteLoan() {
		assert(false);
	}
}
