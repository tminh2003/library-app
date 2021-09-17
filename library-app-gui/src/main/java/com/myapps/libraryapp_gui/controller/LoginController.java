package com.myapps.libraryapp_gui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String loginError,
						Model model) {
        model.addAttribute("error", loginError);
		return "login";
	}
	
	@RequestMapping("/perform_login")
	public String loginDetail() {
		return "index";
	}
}
