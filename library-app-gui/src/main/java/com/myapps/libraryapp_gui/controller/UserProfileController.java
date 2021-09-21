package com.myapps.libraryapp_gui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserProfileController {
	@RequestMapping("/userProfile")
	public String userProfile(@CookieValue(value = "username", defaultValue = "") String username,
								Model model) {
		model.addAttribute("username", username);
		
		RestTemplate restTemplate = new RestTemplate();
		Object user= restTemplate.getForObject(
				"http://localhost:8081/users/" + username, Object.class);
		model.addAttribute("user", user);
		return "userProfile";
	}
}
