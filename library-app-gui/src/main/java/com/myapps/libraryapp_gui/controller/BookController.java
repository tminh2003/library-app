package com.myapps.libraryapp_gui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapps.library_app_shared.model.BookDTO;
import com.myapps.libraryapp_gui.service.BookService;

@Controller
public class BookController {
	@Autowired 
	private BookService bookService;
	
	@RequestMapping("/books")
	public String books(HttpSession session, Model model) {
		BookDTO[] allBooks = bookService.getAllBooks();

		model.addAttribute("username", session.getAttribute("username"));
		model.addAttribute("allBooks", allBooks);
		return "allBooks";
	}
	
	@RequestMapping("/books/{id}")
	public String oneBook(	@PathVariable Long id, 
							HttpSession session, 
							Model model) {
		
		BookDTO bookDTO = bookService.getBookByIdNumber(id);
		
		String username = (String) session.getAttribute("username");
		
		boolean loggedIn = !(username == null);
		
		model.addAttribute("bookCheckedOutByThisUser", false);
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("username", username);
		model.addAttribute("book", bookDTO);
		return "oneBook";
	}
	
	@RequestMapping("/checkOut")
	public String checkOut(	HttpSession session, 
							Model model) {
		
		model.addAttribute("username", session.getAttribute("username"));
		return "oneBook";
	}
	
	@RequestMapping("/returnBook")
	public String returnBook(	HttpSession session, 
								Model model) {
		
		model.addAttribute("username", session.getAttribute("username"));
		return "oneBook";
	}
	
	@RequestMapping("/reportLost")
	public String reportLost(	HttpSession session, 
								Model model) {
		
		model.addAttribute("username", session.getAttribute("username"));
		return "oneBook";
	}
}
