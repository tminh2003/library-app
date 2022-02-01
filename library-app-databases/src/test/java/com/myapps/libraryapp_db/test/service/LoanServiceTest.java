package com.myapps.libraryapp_db.test.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.myapps.library_app_shared.model.CreateLoanDTO;
import com.myapps.library_app_shared.model.DeleteLoanDTO;
import com.myapps.library_app_shared.model.UpdateLoanDTO;
import com.myapps.libraryapp_db.model.Book;
import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.model.User;
import com.myapps.libraryapp_db.repository.BookRepository;
import com.myapps.libraryapp_db.repository.LoanRepository;
import com.myapps.libraryapp_db.repository.UserRepository;
import com.myapps.libraryapp_db.service.LoanService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestConfiguration.class)
public class LoanServiceTest {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

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

		LoanService loanService = new LoanService(loanRepository, userRepository, bookRepository);
		List<Loan> retrievedList = loanService.getAllLoans();

		assert (retrievedList.equals(expectedList));
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
		LoanService loanService = new LoanService(loanRepository, userRepository, bookRepository);
		List<Loan> retrievedList = loanService.getAllLoansFor("test_user1");
		retrievedLoansOfCorrectUser = retrievedList.equals(user1Loans);

		retrievedList = loanService.getAllLoansFor("test_user2");
		retrievedLoansOfCorrectUser &= retrievedList.equals(user2Loans);
		assert (retrievedLoansOfCorrectUser);
	}

	@Test
	public void testCreateLoan_setBookToOutAndChargeUser() {
		LoanService loanService = new LoanService(loanRepository, userRepository, bookRepository);
		
		//Preloading items to database
		bookRepository.save(new Book("123456", "", "", 30, "IN"));
		userRepository.save(new User("test_user1", "", "", "", 100));
		
		//Execute method
		try {
			loanService.createLoan(new CreateLoanDTO("test_user1", "123456", LocalDate.now().plusDays(30)));
		} catch (Exception e) {
		}

		//Retrieve items to check
		Loan retrievedLoan = loanRepository.findByUsername("test_user1").get(0);
		User retrievedUser = userRepository.findByUsername("test_user1");
		Book retrievedBook = bookRepository.findByIsbn("123456");
		
		boolean everythingIsOk = retrievedLoan.getUsername().equals("test_user1");
		everythingIsOk &= retrievedLoan.getBookIsbn().equals("123456");
		everythingIsOk &= retrievedLoan.getDueDate().equals(LocalDate.now().plusDays(30));
		everythingIsOk &= retrievedUser.getFineBalance() == 70;
		everythingIsOk &= retrievedBook.getCurrentStatus().equals("OUT");

		assert (everythingIsOk);
	}
	
	@Test
	public void testUpdateLoan() {
		LoanService loanService = new LoanService(loanRepository, userRepository, bookRepository);
      
		loanRepository.save(new Loan("test_user", "123456", LocalDate.now().plusDays(30)));
		
		//change due date
		loanService.updateLoan(new UpdateLoanDTO("test_user", "123456", LocalDate.now().plusDays(60)));
		Loan retrievedLoan = loanRepository.findByUsername("test_user").get(0);
		
		boolean everythingIsOk = retrievedLoan.getBookIsbn().equals("123456");
		everythingIsOk &= retrievedLoan.getUsername().equals("test_user");
		everythingIsOk &= retrievedLoan.getDueDate().equals(LocalDate.now().plusDays(60));
		assert(everythingIsOk);
	}

	@Test
	public void testDeleteLoan() {
		LoanService loanService = new LoanService(loanRepository, userRepository, bookRepository);

		loanRepository.save(new Loan("test_user", "123456", LocalDate.now()));
		loanService.deleteLoan(new DeleteLoanDTO("test_user", "123456"));

		List<Loan> allLoans = loanRepository.findAll();

		assert (allLoans.size() == 0);
	}
}
