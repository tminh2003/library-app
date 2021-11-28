package com.myapps.libraryapp_db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapps.library_app_shared.model.BookDTO;
import com.myapps.libraryapp_db.model.Book;
import com.myapps.libraryapp_db.repository.BookRepository;
import com.myapps.libraryapp_db.util.BookDTOMapper;

@RestController
public class BookController {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookDTOMapper dtoMapper;
	
	@GetMapping("/books")
	@Transactional(timeout = 4000)
	public List<BookDTO> getAll() {
		return dtoMapper.toDTO(bookRepository.findAll());
	}
	
	@PutMapping("/books")
	public void updateOne(@RequestBody BookDTO bookDTO) {
		Book book = bookRepository.getById(bookDTO.getId());
		book.setAuthor(bookDTO.getAuthor());
		book.setTitle(bookDTO.getTitle());
		book.setIsbn(bookDTO.getIsbn());
		book.setCost(bookDTO.getCost());
		book.setCurrentStatus(bookDTO.getCurrentStatus());
		bookRepository.save(book);
	}

	@GetMapping("/books/{id}")
	public BookDTO one(@PathVariable Long id) {

		return dtoMapper.toDTO(bookRepository.findById(id).get());
	}
}
