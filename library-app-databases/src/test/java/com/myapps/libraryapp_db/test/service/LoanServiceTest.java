package com.myapps.libraryapp_db.test.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.myapps.library_app_shared.model.CreateLoanDTO;
import com.myapps.library_app_shared.model.DeleteLoanDTO;
import com.myapps.library_app_shared.model.UpdateLoanDTO;
import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.repository.LoanRepository;
import com.myapps.libraryapp_db.service.LoanService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanServiceTest {
	
	@Autowired
	private LoanRepository loanRepository;
	
	@BeforeEach
	public void clearLoanRepository() {
		loanRepository.deleteAll();
	}
	
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
	public void testCreateLoan() {
		LoanService loanService = new LoanService(loanRepository);
		
		String username = "test_user1";
		String isbn = "123456";
		int howLong = 30;
		LocalDate dueDate = LocalDate.now().plusDays(howLong);
		
		CreateLoanDTO createLoanDTO = new CreateLoanDTO(username, isbn, howLong);
		loanService.createLoan(createLoanDTO);
		
		//Get first element
		Loan retrievedLoan = loanRepository.findByUsername(username).get(0);
		
		boolean everythingIsOk = retrievedLoan.getUsername().equals(username);
		everythingIsOk &= retrievedLoan.getBookIsbn().equals(isbn);
		everythingIsOk &= retrievedLoan.getDueDate().equals(dueDate);
		
		//Make sure user is charged and book is set to out
		
		assert(everythingIsOk);
	}

	@Test
	public void testUpdateLoan() {
		LoanService loanService = new LoanService(loanRepository);
		
		Long loanId = 0L;
		String usernameBefore = "test_user1";
		String isbnBefore = "123456";
		int howLongBefore = 30;
		LocalDate dueDateBefore = LocalDate.now().plusDays(howLongBefore);
		
		String usernameAfter = "test_user1";
		String isbnAfter = "123456";
		int howLongAfter = 60;
		LocalDate dueDateAfter = LocalDate.now().plusDays(howLongAfter);

		loanRepository.save(new Loan(loanId, usernameBefore, isbnBefore, dueDateBefore));
		loanService.updateLoan(new UpdateLoanDTO(loanId , usernameAfter, isbnAfter, howLongAfter));


		Loan expectedLoan = new Loan(loanId, usernameAfter, isbnAfter, dueDateAfter);
		Loan retrievedLoan = loanRepository.findByUsername(usernameAfter).get(0);

		assert(retrievedLoan.equals(expectedLoan));
	}

	@Test
	public void testDeleteLoan() {
		LoanService loanService = new LoanService(loanRepository);
		
		loanRepository.save(new Loan("test_user", "123456", LocalDate.now()));
		loanService.deleteLoan(new DeleteLoanDTO("test_user", "123456"));
		
		List<Loan> allLoans = loanRepository.findAll();
		
		assert(allLoans.size() == 0);
	}
}
