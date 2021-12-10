package com.myapps.libraryapp_gui.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.UserDTO;
import com.myapps.libraryapp_gui.exception.UsernameAlreadyExistsException;
;

public class UserService{
	private String RESOURCE_LOCATION;
	private PasswordEncoder passwordEncoder;
	
	public UserService(String RESOURCE_LOCATION, PasswordEncoder passwordEncoder) {
		this.RESOURCE_LOCATION = RESOURCE_LOCATION;
		this.passwordEncoder = passwordEncoder;
	}

	public UserDTO getUserByUsername(String username) throws UsernameNotFoundException{
		RestTemplate restTemplate = new RestTemplate();
		UserDTO user = restTemplate.getForEntity(
				RESOURCE_LOCATION + "/" + username, UserDTO.class).getBody();
		
		if(user == null) throw new UsernameNotFoundException(username);
		
		return user;
	}
	
	public void addUser(String username, String email, String password, String authority) throws UsernameAlreadyExistsException {
		try {
			getUserByUsername(username); //will throw exception if username not found
			throw new UsernameAlreadyExistsException();
		}catch(UsernameNotFoundException exception) {
			RestTemplate restTemplate = new RestTemplate();
			UserDTO user = new UserDTO(	username, 
									email, 
									passwordEncoder.encode(password), 
									authority);
			restTemplate.postForObject(RESOURCE_LOCATION, user, UserDTO.class);
		}
	}
}
