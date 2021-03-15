package controller.addPatron;

import org.junit.BeforeClass;
import org.junit.Test;

import commandLine.CommandList;
import minhTo.commandLineAddOn.ErrorAwareCommandList;
import minhTo.libraryApp.controller.addPatron.AddPatronCommand;
import testUtil.SessionFactoryMockInjector;

public class DefaultOptionTest {
	private static CommandList commandList;
	
	@BeforeClass
	public static void setUp() {
		commandList = new ErrorAwareCommandList();
		AddPatronCommand command = new AddPatronCommand();
		command.setDatabaseSessionFactory(SessionFactoryMockInjector.getSessionFactory());
		commandList.register(command);
	}
	
	@Test
	public void testDefaultOption_default() {
		commandList.execute("ap Jack Frost, jackfrost@gmail.com");
	}
	
	
}	
