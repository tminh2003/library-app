package com.myapps.libraryapp_db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapps.library_app_shared.model.UserDTO;
import com.myapps.libraryapp_db.repository.UserRepository;
import com.myapps.libraryapp_db.util.UserDTOMapper;

@RestController
public class UserController {
	@Autowired 
	private UserRepository userRepository;

	@Autowired
	private UserDTOMapper dtoMapper;
	
	@GetMapping("/users")
	@Transactional(timeout = 4000)
	public List<UserDTO> getAll() {
		return dtoMapper.toDTO(userRepository.findAll());
	}
	
	@PostMapping("/users")
	public void createUser(	@RequestBody UserDTO user) {
		userRepository.save(dtoMapper.toEntity(user));
	}
	
	@GetMapping("/users/{username}")
	public UserDTO one(@PathVariable String username) {
		return dtoMapper.toDTO(userRepository.findByName(username));
	}
	
	
}
