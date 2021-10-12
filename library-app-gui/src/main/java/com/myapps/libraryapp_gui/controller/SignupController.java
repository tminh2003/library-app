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
import com.myapps.libraryapp_gui.model.UserValidationDetails;
import com.myapps.libraryapp_gui.service.UserService;

@Controller
public class SignupController {
	@Autowired
	private UserService userService; 
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		UserValidationDetails user = new UserValidationDetails();
		
		model.addAttribute("user", user);
		return "signup";
	}
	
	@RequestMapping("/signupProcessing")
	public String signupProcessing(	@ModelAttribute("user") @Valid UserValidationDetails user,
									Errors errors) {
		if(errors.getGlobalError() != null) {
			return "redirect:signup?error=passwordMismatch";
		}if(errors.getFieldError("email") != null) {
			return "redirect:signup?error=invalidEmail";
		}
		
		try {
			userService.addUser(user.getUsername(),
								user.getEmail(),
								user.getPassword(),
								"USER");
		}catch(UsernameAlreadyExistsException ex) {
			return "redirect:signup?error=usernameAlreadyExists";
		}
		return "redirect:";
	}
	
}
