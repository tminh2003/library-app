package com.myapps.libraryapp_gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	@Autowired
	UserDetailsService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetails user;
		try {
			user = userService.loadUserByUsername(authentication.getName());
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
