package com.myapps.libraryapp_db.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapps.library_app_shared.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}