package controller.addPatron;

import org.junit.Test;

import commandLine.CommandList;
import minhTo.libraryApp.ErrorAwareCommandList;
import minhTo.libraryApp.controller.addPatron.AddPatronCommand;

public class DefaultOptionTest {

	
	@Test
	public void testDefaultOption() {
		CommandList commandList = new ErrorAwareCommandList();
		commandList.register(new AddPatronCommand());
		commandList.execute("ap Jack Frost");
	}
}	
