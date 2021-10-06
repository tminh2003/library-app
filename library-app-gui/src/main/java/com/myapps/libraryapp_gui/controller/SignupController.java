package com.myapps.libraryapp_gui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapps.libraryapp_gui.UserDTO;
import com.myapps.libraryapp_gui.exception.UsernameAlreadyExistsException;
import com.myapps.libraryapp_gui.security.UserService;

@Controller
public class SignupController {
	@Autowired
	UserService userService; 
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		UserDTO user = new UserDTO();
		model.addAttribute("user", user);
		
		return "signup";
	}
	
	@RequestMapping("/signupProcessing")
	public String signupProcessing(	@ModelAttribute("user") @Valid UserDTO userDTO,
									Error error) {
		try {
			userService.addUser(userDTO);
		}catch(UsernameAlreadyExistsException ex) {
			return "redirect:signup?error=usernameAlreadyExists";
		}
		return "redirect:";
	}
	
}
