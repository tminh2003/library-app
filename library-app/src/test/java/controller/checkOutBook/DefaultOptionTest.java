package controller.checkOutBook;

import org.junit.Test;

import commandLine.CommandList;
import minhTo.libraryApp.controller.checkOutBook.CheckOutBookCommand;
import testUtil.SessionFactoryMockInjector;

public class DefaultOptionTest {
	
	@Test
	public void testDefaultOption() {
		CommandList commandList = new CommandList();
		CheckOutBookCommand checkOutBook = new CheckOutBookCommand();
		checkOutBook.setDatabaseSessionFactory(SessionFactoryMockInjector.getSessionFactory());
		commandList.register(checkOutBook);
		
		commandList.execute("o Jack Frost, Lord of the Rings ");
	}
	

	
}
