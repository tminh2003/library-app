package com.myapps.libraryapp_gui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.myapps.library_app_shared.model.User;
import com.myapps.libraryapp_gui.model.UserSecurityDetails;

@Component
public class UserSecurityDetailsService implements UserDetailsService{
	@Autowired
	private UserService userService;
	
	public UserDetails loadUserByUsername(String username){
		User user = userService.getUserByUsername(username);
		
		return new UserSecurityDetails(user.getName(), user.getPassword(), user.getAuthority());
	}
}
