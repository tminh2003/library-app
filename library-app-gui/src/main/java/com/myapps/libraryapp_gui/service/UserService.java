package com.myapps.libraryapp_gui.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.User;
import com.myapps.libraryapp_gui.exception.UsernameAlreadyExistsException;
;

public class UserService{
	
	public User getUserByUsername(String username) throws UsernameNotFoundException{
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForEntity(
				"http://localhost:8081/users/" + username, User.class).getBody();
		
		if(user == null) throw new UsernameNotFoundException(username);
		
		return user;
	}
	
	public void addUser(String username, String email, String encryptedPassword, String authority) throws UsernameAlreadyExistsException {
		try {
			getUserByUsername(username); //will throw exception if username not found
			throw new UsernameAlreadyExistsException();
		}catch(UsernameNotFoundException exception) {
			RestTemplate restTemplate = new RestTemplate();
			User user = new User(	username, 
									email, 
									encryptedPassword, 
									authority);
			restTemplate.postForObject("http://localhost:8081/users", user, User.class);
		}
	}
}
