package com.myapps.libraryapp_db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapps.library_app_shared.model.LoanDTO;
import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.repository.LoanRepository;
import com.myapps.libraryapp_db.service.LoanService;
import com.myapps.libraryapp_db.util.LoanDTOMapper;

@RestController
public class LoanController {
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private LoanDTOMapper dtoMapper;
	
	@GetMapping("/loans")
	@Transactional(timeout = 4000)
	public List<Loan> getAll() {
		return loanRepository.findAll();
	}
	
	@GetMapping("/loans/{username}")
	public List<LoanDTO> getAllLoansForUser(@PathVariable String username) {
		return dtoMapper.toDTO(loanRepository.findByUsername(username));
	}
	
	@PostMapping("/loans")
	public void createLoan(@RequestBody LoanDTO loan) {
		loanRepository.save(dtoMapper.toEntity(loan));
	}
	
	@PutMapping("/loans")
	public void updateLoan(@RequestBody LoanDTO loan) {
		loanRepository.save(dtoMapper.toEntity(loan));
	}
	
	@DeleteMapping("/loans")
	public void deleteLoanFor(@RequestParam String username, @RequestParam String bookIsbn) {
		List<Loan> allUserLoan = loanRepository.findByUsername(username);
		for(Loan loan : allUserLoan) {
			if(loan.getBookIsbn().equals(bookIsbn)) {
				loanRepository.delete(loan);
			}
		}
	}

	
}
