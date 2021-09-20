package com.myapps.libraryapp_gui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserProfileController {
	@RequestMapping("/userProfile/{username}")
	public String userProfile(@PathVariable String username, 
						Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Object user= restTemplate.getForObject(
				"http://localhost:8081/users/" + username, Object.class);
		model.addAttribute("user", user);
		return "userProfile";
	}
}
