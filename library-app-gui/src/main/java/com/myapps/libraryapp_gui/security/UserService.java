package com.myapps.libraryapp_gui.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.User;
import com.myapps.libraryapp_gui.UserDTO;
import com.myapps.libraryapp_gui.exception.UsernameAlreadyExistsException;
;

@Component
public class UserService implements UserDetailsService{
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForEntity(
				"http://localhost:8081/users/" + username, User.class).getBody();
		
		if(user == null)
			throw new UsernameNotFoundException(username);
		
		return new CustomUserDetails(user.getName(), user.getPassword(), user.getAuthority());
	}
	
	public void addUser(UserDTO userDTO) throws UsernameAlreadyExistsException {
		try {
			loadUserByUsername(userDTO.getUsername()); //if username not used we could create user
			throw new UsernameAlreadyExistsException();
		}catch(UsernameNotFoundException exception) {
			RestTemplate restTemplate = new RestTemplate();
			User user = new User(	userDTO.getUsername(), 
									userDTO.getEmail(), 
									passwordEncoder.encode(userDTO.getPassword()), 
									"USER");
			restTemplate.postForObject("http://localhost:8081/users", user, User.class);
		}
	}
}
