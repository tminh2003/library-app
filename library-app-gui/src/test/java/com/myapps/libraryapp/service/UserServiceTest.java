package com.myapps.libraryapp.service;

import org.junit.jupiter.api.Test;

import com.myapps.libraryapp_gui.service.UserService;

public class UserServiceTest {
	
	@Test
	public void testChargeUser() {
		UserService userService = new UserService("http://localhost:8081/users", null);
		userService.chargeUser("jack_frost", 10);
	}
	
}
