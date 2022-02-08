package com.myapps.libraryapp_db.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.myapps.library_app_shared.model.CreateLoanDTO;
import com.myapps.library_app_shared.model.DeleteLoanDTO;
import com.myapps.library_app_shared.model.UpdateLoanDTO;
import com.myapps.libraryapp_db.model.Book;
import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.model.User;
import com.myapps.libraryapp_db.repository.BookRepository;
import com.myapps.libraryapp_db.repository.LoanRepository;
import com.myapps.libraryapp_db.repository.UserRepository;

@Transactional
public class LoanService {
	private LoanRepository loanRepository;
	private UserRepository userRepository;
	private BookRepository bookRepository;
	
	private Logger logger = LogManager.getLogger(LoanService.class);
	
	public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
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

	public void createLoan(CreateLoanDTO createLoanDTO){
		loanRepository.save(new Loan(	createLoanDTO.getUsername(), 
										createLoanDTO.getIsbn(), 
										createLoanDTO.getDueDate()));
		Book book = bookRepository.findByIsbn(createLoanDTO.getIsbn());
		User user = userRepository.findByUsername(createLoanDTO.getUsername());

		book.setCurrentStatus("OUT");
		user.setFineBalance(user.getFineBalance() - book.getCost());

		userRepository.save(user);
		bookRepository.save(book);
		
		logger.info("Loan created for " + createLoanDTO.toString());
	}

	public void updateLoan(UpdateLoanDTO updateLoanDTO) {
		List<Loan> allLoansOfUser = getAllLoansFor(updateLoanDTO.getUsername());
		for(Loan loan : allLoansOfUser) {
			if(loan.getBookIsbn() == updateLoanDTO.getIsbn()) {
				loanRepository.save(new Loan(	
						loan.getId(),
						updateLoanDTO.getUsername(),
						updateLoanDTO.getIsbn(),
						updateLoanDTO.getDueDate()));
				logger.info("Loan updated for " + updateLoanDTO.toString());
			}
		}
		
	}

	public void deleteLoan(DeleteLoanDTO deleteLoanDTO) {
		List<Loan> allUserLoan = loanRepository.findByUsername(deleteLoanDTO.getUsername());
		for(Loan loan : allUserLoan) {
			if(loan.getBookIsbn().equals(deleteLoanDTO.getIsbn())) {
				loanRepository.delete(loan);
				logger.info("Loan deleted for " + deleteLoanDTO.toString());
			}
		}
	}

}
