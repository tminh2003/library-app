package com.myapps.libraryapp_gui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapps.libraryapp_gui.exception.UsernameAlreadyExistsException;
import com.myapps.libraryapp_gui.model.UserDTO;
import com.myapps.libraryapp_gui.service.UserService;

@Controller
public class SignupController {
	@Autowired
	UserService userService; 
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		UserDTO user = new UserDTO();
		
		model.addAttribute("user", user);
		return "signup";
	}
	
	@RequestMapping("/signupProcessing")
	public String signupProcessing(	@ModelAttribute("user") @Valid UserDTO userDTO,
									Errors errors) {
		if(errors.getGlobalError() != null) {
			return "redirect:signup?error=passwordMismatch";
		}if(errors.getFieldError("email") != null) {
			return "redirect:signup?error=invalidEmail";
		}
		
		try {
			userService.addUser(userDTO.getUsername(),
								userDTO.getEmail(),
								passwordEncoder.encode(userDTO.getPassword()),
								"USER");
		}catch(UsernameAlreadyExistsException ex) {
			return "redirect:signup?error=usernameAlreadyExists";
		}
		return "redirect:";
	}
	
}
