package com.myapps.libraryapp_db.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initBookDatabase(BookRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Book("LOTR", "Tolkien", "123456", 12.00)));
			log.info("Preloading " + repository.save(new Book("Harry Potter", "Rowling", "123457", 12.00)));
		};
	}

	@Bean
	CommandLineRunner initUserDatabase(UserRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new User("jack frost", "jackfrost@gmail.com", "", "USER")));
			log.info("Preloading " + repository.save(new User("santa claus", "santa claus@gmail.com", "", "ADMIN")));
		};
	}
}
