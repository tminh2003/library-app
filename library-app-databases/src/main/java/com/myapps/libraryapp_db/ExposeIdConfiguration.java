package com.myapps.libraryapp_db;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.myapps.library_app_shared.model.Book;
import com.myapps.library_app_shared.model.User;

public class ExposeIdConfiguration implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		System.out.println("here");
		config.exposeIdsFor(User.class, Book.class);
		
	}
}
