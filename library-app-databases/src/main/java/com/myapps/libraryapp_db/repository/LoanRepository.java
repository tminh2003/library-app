package com.myapps.libraryapp_db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapps.libraryapp_db.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

	public List<Loan> findByUsername(String username);

}