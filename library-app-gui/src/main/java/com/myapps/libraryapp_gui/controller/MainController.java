package com.myapps.libraryapp_gui.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapps.libraryapp_gui.service.ActiveUserService;

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
