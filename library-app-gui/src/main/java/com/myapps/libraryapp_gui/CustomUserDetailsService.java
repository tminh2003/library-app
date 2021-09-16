package com.myapps.libraryapp_gui;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class CustomUserDetailsService implements UserDetailsService{

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RestTemplate restTemplate = new RestTemplate();
		Object[] obj = restTemplate.getForEntity(
				"http://localhost:8081/users", Object[].class).getBody();
		
		System.out.println(obj);
		
		return new CustomUserDetails("","","");
	}
	
	
}
