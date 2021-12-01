package com.myapps.libraryapp_db.util;

import java.util.ArrayList;
import java.util.List;

import com.myapps.library_app_shared.model.BookDTO;
import com.myapps.libraryapp_db.model.Book;

public class BookDTOMapper {
	public BookDTO toDTO(Book book) {
		return new BookDTO(	book.getTitle(),
							book.getAuthor(),
							book.getIsbn(),
							book.getCost(),
							book.getCurrentStatus());
	}
	
	public List<BookDTO> toDTO(List<Book> allBooks){
		
		List<BookDTO> allDTOs= new ArrayList<BookDTO>();
		for(Book Book : allBooks) {
			allDTOs.add(toDTO(Book));
		}
		
		return allDTOs;
	}
	
	public Book toEntity(BookDTO bookDTO) {
		return new Book(bookDTO.getTitle(),
						bookDTO.getAuthor(),
						bookDTO.getIsbn(),
						bookDTO.getCost(),
						bookDTO.getCurrentStatus());
	}
}
