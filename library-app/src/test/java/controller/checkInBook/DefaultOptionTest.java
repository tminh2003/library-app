package controller.checkInBook;

import org.junit.Test;

import commandLine.CommandList;
import minhTo.libraryApp.controller.checkInBook.CheckInBookCommand;
import minhTo.libraryApp.controller.checkOutBook.CheckOutBookCommand;
import testUtil.SessionFactoryMockInjector;

public class DefaultOptionTest {

	@Test
	public void testDefaultOption() {
		CommandList commandList = new CommandList();
		CheckInBookCommand checkInBook = new CheckInBookCommand();
		checkInBook.setDatabaseSessionFactory(SessionFactoryMockInjector.getSessionFactory());
		commandList.register(checkInBook);
		
		commandList.execute("o Jack Frost, Lord of the Rings ");
	}
}
