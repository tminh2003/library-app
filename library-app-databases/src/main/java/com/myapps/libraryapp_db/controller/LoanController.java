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
import org.springframework.web.bind.annotation.RestController;

import com.myapps.library_app_shared.model.CreateLoanDTO;
import com.myapps.library_app_shared.model.DeleteLoanDTO;
import com.myapps.library_app_shared.model.LoanDTO;
import com.myapps.library_app_shared.model.UpdateLoanDTO;
import com.myapps.libraryapp_db.service.LoanService;
import com.myapps.libraryapp_db.util.LoanDTOMapper;

@RestController
public class LoanController {
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private LoanDTOMapper dtoMapper;
	
	@GetMapping("/loans")
	@Transactional(timeout = 4000)
	public List<LoanDTO> getAll() {
		return dtoMapper.toDTO(loanService.getAllLoans());
	}
	
	@GetMapping("/loans/{username}")
	public List<LoanDTO> getAllLoansForUser(@PathVariable String username) {
		return dtoMapper.toDTO(loanService.getAllLoansFor(username));
	}
	
	@PostMapping("/loans")
	public void createLoan(@RequestBody CreateLoanDTO createLoanDTO) {
		loanService.createLoan(createLoanDTO);
	}
	
	@PutMapping("/loans")
	public void updateLoan(@RequestBody UpdateLoanDTO updateLoanDTO) {
		loanService.updateLoan(updateLoanDTO);
	}
	
	@DeleteMapping("/loans")
	public void deleteLoanFor(@RequestBody DeleteLoanDTO deleteLoanDTO) {
		loanService.deleteLoan(deleteLoanDTO);
	}

	
}
