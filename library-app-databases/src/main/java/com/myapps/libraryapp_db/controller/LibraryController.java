package com.myapps.libraryapp_db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapps.library_app_shared.model.CheckOutBookDTO;
import com.myapps.library_app_shared.model.LoanDTO;
import com.myapps.library_app_shared.model.RecheckBookDTO;
import com.myapps.library_app_shared.model.ReturnBookDTO;
import com.myapps.libraryapp_db.service.LibraryService;
import com.myapps.libraryapp_db.util.LoanDTOMapper;

@RestController
public class LibraryController {
	@Autowired
	private LibraryService loanService;
	
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
	
	@PostMapping("/loans/checkOut")
	public void checkOut(@RequestBody CheckOutBookDTO checkOutBookDTO) {
		loanService.checkOutFor(checkOutBookDTO);
	}
	
	@PostMapping("/loans/recheck")
	public void recheckFor(@RequestBody RecheckBookDTO recheckBookDTO) {
		loanService.recheckFor(recheckBookDTO);
	}
	
	@PostMapping("/loans/return")
	public void returnFor(@RequestBody ReturnBookDTO returnBookDTO) {
		loanService.returnFor(returnBookDTO);
	}

	
}
