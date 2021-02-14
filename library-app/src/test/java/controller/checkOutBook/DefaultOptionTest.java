package controller.checkOutBook;

import org.junit.Test;

import commandLine.CommandList;
import controller.SessionFactoryMockInjector;
import minhTo.libraryApp.controller.checkOutBook.CheckOutBookCommand;

public class DefaultOptionTest {
	@Test
	public void testDefaultOption() {
		CommandList commandList = new CommandList();
		CheckOutBookCommand checkOutBook = new CheckOutBookCommand();
		checkOutBook.setSessionFactory(SessionFactoryMockInjector.getSessionFactory());
		commandList.register(checkOutBook);
		
		commandList.execute("o Jack Frost, Lord of the Rings ");
	}
	

	
}
