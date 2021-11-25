package com.myapps.libraryapp_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapps.libraryapp_db.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}