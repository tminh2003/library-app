package com.myapps.libraryapp_gui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapps.library_app_shared.model.UserDTO;
import com.myapps.libraryapp_gui.service.UserService;

@Controller
public class UserProfileController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/userProfile")
	public String userProfile(	HttpSession session,
								Model model) {
		String username = (String) session.getAttribute("username");
		
		//If not logged in
		if(username == null) {
			return "redirect:/login";
		}
		
		UserDTO user = userService.getUserByUsername(username);
		
		model.addAttribute("user", user);
		model.addAttribute("username", username);
		return "userProfile";
	}
}
