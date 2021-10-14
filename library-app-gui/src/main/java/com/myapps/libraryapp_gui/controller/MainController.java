package com.myapps.libraryapp_gui.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {
	@Autowired 
	@Lazy
	private UserDetailsService userService;
	
	@RequestMapping("/")
	public String index(@CookieValue(value = "username", defaultValue = "") String username, Model model) {
		model.addAttribute("username", username);
		
		return "index";
	}
	
	@RequestMapping("/test")
	public String test(@CookieValue(value = "counter", defaultValue = "0") Long counter,
								HttpServletResponse response, Model model) {
		
		counter++;
		
		Cookie cookie = new Cookie("counter", counter.toString());
		cookie.setMaxAge(1000);
		
		response.addCookie(cookie);
		
		model.addAttribute("info", counter);
		return "test";
	}

}
