package com.myapps.libraryapp_gui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String loginError,
						@CookieValue(value = "username", defaultValue = "") String username,
						Model model) {
		if(username != null)
			return "redirect:";
		
        model.addAttribute("error", loginError);
		return "login";
	}
}
