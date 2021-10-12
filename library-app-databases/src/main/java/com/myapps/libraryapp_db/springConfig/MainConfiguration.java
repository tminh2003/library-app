package com.myapps.libraryapp_db.springConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myapps.libraryapp_db.util.BookDTOMapper;
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
}
