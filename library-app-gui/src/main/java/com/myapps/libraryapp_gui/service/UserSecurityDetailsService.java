package com.myapps.libraryapp_gui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.myapps.library_app_shared.model.UserDTO;
import com.myapps.libraryapp_gui.model.UserSecurityDetails;

public class UserSecurityDetailsService implements UserDetailsService{
	@Autowired
	private UserService userService;
	
	public UserDetails loadUserByUsername(String username){
		UserDTO user = userService.getUserByUsername(username);
		
		return new UserSecurityDetails(user.getUsername(), user.getPassword(), user.getAuthority());
	}
}
