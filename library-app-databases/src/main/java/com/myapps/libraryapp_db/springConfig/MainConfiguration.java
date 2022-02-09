package com.myapps.libraryapp_db.springConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myapps.libraryapp_db.repository.BookRepository;
import com.myapps.libraryapp_db.repository.LoanRepository;
import com.myapps.libraryapp_db.repository.UserRepository;
import com.myapps.libraryapp_db.service.LibraryService;
import com.myapps.libraryapp_db.util.BookDTOMapper;
import com.myapps.libraryapp_db.util.LoanDTOMapper;
import com.myapps.libraryapp_db.util.UserDTOMapper;

@Configuration
public class MainConfiguration {

	@Bean 
	public BookDTOMapper bookDTOMapper() {
		return new BookDTOMapper();
	}
	
	@Bean 
	public UserDTOMapper userDTOMapper() {
		return new UserDTOMapper();
	}
	
	@Bean
	public LoanDTOMapper loanDTOMapper() {
		return new LoanDTOMapper();
	}
	
	@Bean
	public LibraryService libraryService(LoanRepository loanRepo, UserRepository userRepo, BookRepository bookRepo) {
		return new LibraryService(loanRepo, userRepo, bookRepo);
	}
}
