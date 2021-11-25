package com.myapps.libraryapp_db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.repository.LoanRepository;

@RestController
public class LoanController {
	@Autowired
	private LoanRepository loanRepository;
	
	@GetMapping("/loans")
	@Transactional(timeout = 4000)
	public List<Loan> getAll() {
		return loanRepository.findAll();
	}

	@GetMapping("/loans/{id}")
	public Loan one(@PathVariable Long id) {
		return loanRepository.findById(id).get();
	}
}
