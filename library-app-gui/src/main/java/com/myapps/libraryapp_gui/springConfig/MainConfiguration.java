package com.myapps.libraryapp_gui.springConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myapps.libraryapp_gui.service.UserService;

@Configuration
public class MainConfiguration {

	@Bean
	public UserService userService() {
		return new UserService();
	}
	
}
