package com.myapps.libraryapp_gui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@Autowired 
	@Lazy
	private UserDetailsService userService;
	
	@RequestMapping("/")
	public String index(HttpSession session, Model model) {
		model.addAttribute("username", session.getAttribute("username"));
		return "index";
	}
}
