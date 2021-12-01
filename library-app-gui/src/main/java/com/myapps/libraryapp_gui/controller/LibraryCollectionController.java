package com.myapps.libraryapp_gui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapps.library_app_shared.model.BookDTO;
import com.myapps.library_app_shared.model.LoanDTO;
import com.myapps.libraryapp_gui.service.BookService;
import com.myapps.libraryapp_gui.service.LibraryService;
import com.myapps.libraryapp_gui.service.LoanService;

@Controller
public class LibraryCollectionController {
	@Autowired 
	private BookService bookService;
	
	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private LoanService loanService;
	
	@RequestMapping("/books")
	public String books(HttpSession session, Model model) {
		BookDTO[] allBooks = bookService.getAllBooks();

		model.addAttribute("username", session.getAttribute("username"));
		model.addAttribute("allBooks", allBooks);
		return "allBooks";
	}
	//----------------------------------------------------------------------------------------------
	@RequestMapping("/books/{isbn}")
	public String oneBook(@PathVariable String isbn, HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		
		LoanDTO[] thisUsersLoans = loanService.getAllLoansForUser(username);
		boolean bookCheckedOutByThisUser = false;
		
		for(LoanDTO loanDTO : thisUsersLoans) {
			if(loanDTO.getBookIsbn().equals(isbn))
				bookCheckedOutByThisUser = true;
		}
		
		model.addAttribute("bookCheckedOutByThisUser", bookCheckedOutByThisUser);
		model.addAttribute("loggedIn", username != null);
		model.addAttribute("username", username);
		model.addAttribute("book", bookService.getBookByIsbn(isbn));
		return "oneBook";
	}
	//----------------------------------------------------------------------------------------------
	@RequestMapping("/checkOut/{isbn}")
	public String checkOut(	@PathVariable String isbn, HttpSession session, Model model) {

		libraryService.checkOutBookForUser(isbn, session.getAttribute("username").toString(), 30);
		model.addAttribute("username", session.getAttribute("username"));
		return "redirect:/books/" + isbn;
	}
	//----------------------------------------------------------------------------------------------
	@RequestMapping("/returnBook")
	public String returnBook(HttpSession session, Model model) {
		model.addAttribute("username", session.getAttribute("username"));
		return "allBooks";
	}
	//----------------------------------------------------------------------------------------------	
	@RequestMapping("/reportLost")
	public String reportLost(HttpSession session, Model model) {
		
		model.addAttribute("username", session.getAttribute("username"));
		return "allBooks";
	}
}
