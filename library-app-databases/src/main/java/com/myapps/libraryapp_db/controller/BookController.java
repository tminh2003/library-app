package com.myapps.libraryapp_db.controller;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.myapps.libraryapp_db.model.Book;
import com.myapps.libraryapp_db.model.BookRepository;

@RestController
public class BookController {
	private final BookRepository bookRepository;

	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GetMapping("/books")
	@Transactional(timeout = 4000)
	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	@GetMapping("/books/{id}")
	public Book one(@PathVariable Long id) {

		return bookRepository.findById(id).get();
	}
}
