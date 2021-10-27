package com.myapps.libraryapp_gui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login(HttpSession session,
						@RequestParam(value = "error", required = false) String loginError,
						Model model) {
		
		//If already logged in
		if(!(session.getAttribute("username") == null))
			return "redirect:";
		
        model.addAttribute("error", loginError);
		return "login";
	}
}
