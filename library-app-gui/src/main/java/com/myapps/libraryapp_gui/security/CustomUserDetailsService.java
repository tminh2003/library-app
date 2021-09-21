package com.myapps.libraryapp_gui.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.myapps.library_app_shared.model.User;
;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForEntity(
				"http://localhost:8081/users/" + username, User.class).getBody();
		
		if(user == null) throw new UsernameNotFoundException(username);
		
		return new CustomUserDetails(user.getName(), user.getPassword(), user.getAuthority());
	}
}
