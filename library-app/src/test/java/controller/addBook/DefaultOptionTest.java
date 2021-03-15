package controller.addBook;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commandLine.CommandList;
import minhTo.commandLineAddOn.ErrorAwareCommandList;
import minhTo.libraryApp.controller.addBook.AddBookCommand;

public class DefaultOptionTest {
	private static CommandList commandList;
	
	@BeforeAll
	public static void setUp() {
		commandList = new ErrorAwareCommandList();
		AddBookCommand command = new AddBookCommand();
		commandList.register(command);
	}
	
	@Test
	public void testDefault() {
		commandList.execute("ab Harry Potter,  J.K. Rowling, 1234590, 12.00");
	}
}
