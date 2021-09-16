package com.myapps.libraryapp_db.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Book, Long> {

}