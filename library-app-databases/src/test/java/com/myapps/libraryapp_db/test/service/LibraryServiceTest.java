package com.myapps.libraryapp_db.test.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myapps.library_app_shared.model.CheckOutBookDTO;
import com.myapps.library_app_shared.model.RecheckBookDTO;
import com.myapps.library_app_shared.model.ReturnBookDTO;
import com.myapps.libraryapp_db.model.Book;
import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.model.User;
import com.myapps.libraryapp_db.repository.BookRepository;
import com.myapps.libraryapp_db.repository.LoanRepository;
import com.myapps.libraryapp_db.repository.UserRepository;
import com.myapps.libraryapp_db.service.LibraryService;

@SpringBootTest
public class LibraryServiceTest {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	@BeforeEach
	public void clearLoanRepository() {
		loanRepository.deleteAll();
		userRepository.deleteAll();
		bookRepository.deleteAll();
	}

	@Test
	public void testGetAllLoans() {
		LoanRepository loanRepository = mock(LoanRepository.class);
		List<Loan> expectedList = new ArrayList<Loan>();
		expectedList.add(new Loan(1L, "test_user", "123456", LocalDate.now()));

		when(loanRepository.findAll()).thenReturn(expectedList);

		LibraryService libraryService = new LibraryService(loanRepository, userRepository, bookRepository);
		List<Loan> retrievedList = libraryService.getAllLoans();

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
		LibraryService libraryService = new LibraryService(loanRepository, userRepository, bookRepository);
		List<Loan> retrievedList = libraryService.getAllLoansFor("test_user1");
		retrievedLoansOfCorrectUser = retrievedList.equals(user1Loans);

		retrievedList = libraryService.getAllLoansFor("test_user2");
		retrievedLoansOfCorrectUser &= retrievedList.equals(user2Loans);
		assert (retrievedLoansOfCorrectUser);
	}

	@Test
	public void testCheckOutBook_setBookToOutAndChargeUser() {
		LibraryService libraryService = new LibraryService(loanRepository, userRepository, bookRepository);

		// Preloading items to database
		bookRepository.save(new Book("123456", "", "", 30, "IN"));
		userRepository.save(new User("test_user1", "", "", "", 100));

		// Execute method
		libraryService.checkOutFor(new CheckOutBookDTO("test_user1", "123456", LocalDate.now().plusDays(30)));

		// Retrieve items to check
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
	public void testRecheckBook() {
		LibraryService libraryService = new LibraryService(loanRepository, userRepository, bookRepository);

		loanRepository.save(new Loan("test_user", "123456", LocalDate.now().plusDays(30)));

		// change due date
		libraryService.recheckFor(new RecheckBookDTO("test_user", "123456", LocalDate.now().plusDays(60)));
		Loan retrievedLoan = loanRepository.findByUsername("test_user").get(0);

		boolean everythingIsOk = retrievedLoan.getBookIsbn().equals("123456");
		everythingIsOk &= retrievedLoan.getUsername().equals("test_user");
		everythingIsOk &= retrievedLoan.getDueDate().equals(LocalDate.now().plusDays(60));
		assert (everythingIsOk);
	}

	@Test
	public void testReturnBook() {
		LibraryService libraryService = new LibraryService(loanRepository, userRepository, bookRepository);

		loanRepository.save(new Loan("test_user", "123456", LocalDate.now()));
		bookRepository.save(new Book("123456", "test_book", "author", 15.00, "OUT"));
		
		libraryService.returnFor(new ReturnBookDTO("test_user", "123456"));
		
		List<Loan> allLoans = loanRepository.findAll();
		List<Book> allBooks = bookRepository.findAll();

		boolean everythingIsOk = allLoans.size() == 0;
		everythingIsOk &= allBooks.get(0).getCurrentStatus().equals("IN");
		assert (everythingIsOk);
	}
}
