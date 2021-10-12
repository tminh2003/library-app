package com.myapps.libraryapp_gui.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.UserDTO;
import com.myapps.libraryapp_gui.exception.UsernameAlreadyExistsException;
;

public class UserService{
	
	public UserDTO getUserByUsername(String username) throws UsernameNotFoundException{
		RestTemplate restTemplate = new RestTemplate();
		UserDTO user = restTemplate.getForEntity(
				"http://localhost:8081/users/" + username, UserDTO.class).getBody();
		
		if(user == null) throw new UsernameNotFoundException(username);
		
		return user;
	}
	
	public void addUser(String username, String email, String encryptedPassword, String authority) throws UsernameAlreadyExistsException {
		try {
			getUserByUsername(username); //will throw exception if username not found
			throw new UsernameAlreadyExistsException();
		}catch(UsernameNotFoundException exception) {
			RestTemplate restTemplate = new RestTemplate();
			UserDTO user = new UserDTO(	username, 
									email, 
									encryptedPassword, 
									authority);
			restTemplate.postForObject("http://localhost:8081/users", user, UserDTO.class);
		}
	}
}
