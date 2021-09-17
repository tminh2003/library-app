package com.myapps.libraryapp_db.controller;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.myapps.library_app_shared.model.User;
import com.myapps.libraryapp_db.model.UserRepository;

@RestController
public class UserController {
	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	@Transactional(timeout = 4000)
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{username}")
	public User one(@PathVariable String username) {
		return userRepository.findByName(username);
	}
}
