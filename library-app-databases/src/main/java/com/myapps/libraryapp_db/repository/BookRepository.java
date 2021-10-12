package com.myapps.libraryapp_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapps.libraryapp_db.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}