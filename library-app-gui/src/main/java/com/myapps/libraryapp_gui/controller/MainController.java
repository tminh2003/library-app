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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
						Model model) {
        model.addAttribute("error", error);
		return "login";
	}
	
	@RequestMapping("/perform_login")
	public String loginDetail() {
		return "index";
	}
	
	@RequestMapping("/books")
	public String books(@CookieValue(value = "username", defaultValue = "")String username, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> obj= restTemplate.getForEntity(
				"http://localhost:8081/books", Object[].class);
		model.addAttribute("allBooks", obj.getBody());
		return "allBooks";
	}
	
	@GetMapping("/books/{id}")
	public String oneBook(@PathVariable Long id, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Object obj= restTemplate.getForObject(
				"http://localhost:8081/books/" + id, Object.class);
		model.addAttribute("book", obj);
		return "oneBook";
	}

}
