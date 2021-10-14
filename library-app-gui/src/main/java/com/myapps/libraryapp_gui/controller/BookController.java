package com.myapps.libraryapp_gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.myapps.library_app_shared.model.BookDTO;
import com.myapps.libraryapp_gui.service.BookService;

@Controller
public class BookController {
	@Autowired 
	private BookService bookService;
	
	@RequestMapping("/books")
	public String books(@CookieValue(value = "username", defaultValue = "")String username, Model model) {
		BookDTO[] allBooks = bookService.getAllBooks();

		model.addAttribute("username", username);
		model.addAttribute("allBooks", allBooks);
		return "allBooks";
	}
	
	@RequestMapping("/books/{id}")
	public String oneBook(@PathVariable Long id, 
						@CookieValue(value = "username", defaultValue = "") String username, 
						Model model) {
		
		BookDTO bookDTO = bookService.getBookByIdNumber(id);
		
		model.addAttribute("username", username);
		model.addAttribute("book", bookDTO);
		return "oneBook";
	}
}
