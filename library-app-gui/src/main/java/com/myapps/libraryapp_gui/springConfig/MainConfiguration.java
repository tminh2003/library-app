package com.myapps.libraryapp_gui.springConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.myapps.libraryapp_gui.security.LoginSuccessHandler;
import com.myapps.libraryapp_gui.security.MyLogoutSuccessHandler;
import com.myapps.libraryapp_gui.service.ActiveUserService;
import com.myapps.libraryapp_gui.service.BookService;
import com.myapps.libraryapp_gui.service.UserSecurityDetailsService;
import com.myapps.libraryapp_gui.service.UserService;

@Configuration
public class MainConfiguration {

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserSecurityDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}
	
	@Bean
	public BookService bookService() {
		return new BookService();
	}
	
	@Bean
	public ActiveUserService activeUserService() {
		return new ActiveUserService();
	}
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new MyLogoutSuccessHandler();
	}
}
