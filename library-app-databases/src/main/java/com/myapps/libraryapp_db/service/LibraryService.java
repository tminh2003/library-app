package com.myapps.libraryapp_db.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.myapps.library_app_shared.model.CheckOutBookDTO;
import com.myapps.library_app_shared.model.ReturnBookDTO;
import com.myapps.library_app_shared.model.RecheckBookDTO;
import com.myapps.library_app_shared.model.RecheckBookDTO;
import com.myapps.libraryapp_db.model.Book;
import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.model.User;
import com.myapps.libraryapp_db.repository.BookRepository;
import com.myapps.libraryapp_db.repository.LoanRepository;
import com.myapps.libraryapp_db.repository.UserRepository;

@Transactional
public class LibraryService {
	private LoanRepository loanRepository;
	private UserRepository userRepository;
	private BookRepository bookRepository;
	
	private Logger logger = LogManager.getLogger(LibraryService.class);
	
	public LibraryService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
		this.loanRepository = loanRepository;
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
	}

	public List<Loan> getAllLoans() {
		return loanRepository.findAll();
	}

	public List<Loan> getAllLoansFor(String username) {
		return loanRepository.findByUsername(username);
	}

	public void checkOutFor(CheckOutBookDTO checkOutBookDTO){
		loanRepository.save(new Loan(	checkOutBookDTO.getUsername(), 
										checkOutBookDTO.getIsbn(), 
										checkOutBookDTO.getDueDate()));
		Book book = bookRepository.findByIsbn(checkOutBookDTO.getIsbn());
		User user = userRepository.findByUsername(checkOutBookDTO.getUsername());

		book.setCurrentStatus("OUT");
		user.setFineBalance(user.getFineBalance() - book.getCost());

		userRepository.save(user);
		bookRepository.save(book);
		
		logger.info("Book checked out for " + checkOutBookDTO.toString());
	}

	public void recheckFor(RecheckBookDTO RecheckBookDTO) {
		List<Loan> allLoansOfUser = getAllLoansFor(RecheckBookDTO.getUsername());
		for(Loan loan : allLoansOfUser) {
			if(loan.getBookIsbn() == RecheckBookDTO.getIsbn()) {
				loanRepository.save(new Loan(	
						loan.getId(),
						RecheckBookDTO.getUsername(),
						RecheckBookDTO.getIsbn(),
						RecheckBookDTO.getDueDate()));
				logger.info("Book rechecked for " + RecheckBookDTO.toString());
			}
		}
		
	}

	public void returnFor(ReturnBookDTO returnBookDTO) {
		List<Loan> allUserLoan = loanRepository.findByUsername(returnBookDTO.getUsername());
		for(Loan loan : allUserLoan) {
			if(loan.getBookIsbn().equals(returnBookDTO.getIsbn())) {
				loanRepository.delete(loan);
				logger.info("Book returned for " + returnBookDTO.toString());
			}
		}
		
		Book book = bookRepository.findByIsbn(returnBookDTO.getIsbn());
		book.setCurrentStatus("IN");
		bookRepository.save(book);
	}


}
