package com.myapps.libraryapp_gui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("/logout")
	public String logout() {
		System.out.println("logging out");
		return "logout";
	}
}
