package com.myapps.libraryapp_db.service;

import java.time.LocalDate;
import java.util.List;

import com.myapps.library_app_shared.model.CreateLoanDTO;
import com.myapps.library_app_shared.model.DeleteLoanDTO;
import com.myapps.library_app_shared.model.UpdateLoanDTO;
import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.repository.LoanRepository;

public class LoanService {
	private LoanRepository loanRepository;
	
	public LoanService(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}

	public List<Loan> getAllLoans() {
		return loanRepository.findAll();
	}

	public List<Loan> getAllLoansFor(String username) {
		return loanRepository.findByUsername(username);
	}

	public void createLoan(CreateLoanDTO createLoanDTO) {
		loanRepository.save(new Loan(	createLoanDTO.getUsername(), 
										createLoanDTO.getIsbn(), 
										LocalDate.now().plusDays(createLoanDTO.getHowLong())));
	}

	public void updateLoan(UpdateLoanDTO updateLoanDTO) {
		loanRepository.save(new Loan(	updateLoanDTO.getLoanId(),
										updateLoanDTO.getUsername(),
										updateLoanDTO.getIsbn(),
										LocalDate.now().plusDays(updateLoanDTO.getHowLong())));
	}

	public void deleteLoan(DeleteLoanDTO deleteLoanDTO) {
		List<Loan> allUserLoan = loanRepository.findByUsername(deleteLoanDTO.getUsername());
		for(Loan loan : allUserLoan) {
			if(loan.getBookIsbn().equals(deleteLoanDTO.getIsbn())) {
				System.out.println(loan.toString());
				loanRepository.delete(loan);
			}
		}
	}

}
