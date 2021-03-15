package commandLineAddOn;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import minhTo.libraryApp.exception.NoSuchCommandAddedException;
import testUtil.MockCommandFactory;
import minhTo.commandLineAddOn.ErrorAwareCommandList;
import minhTo.libraryApp.exception.IncorrectNumberOfParamException;

public class ErrorAwareCommandListTest {
	private static ErrorAwareCommandList commandList;
	
	@Before
	public void setUp() {
		commandList = new ErrorAwareCommandList();
	}
	
	@Test
	public void testExecute_default() {
		commandList.register(MockCommandFactory.get("sayHi"));
		commandList.register(MockCommandFactory.get("twoParam"));
		commandList.execute("twoParam one,  two");
		commandList.execute("sayHi");
		commandList.execute("        sayHi");
	}
	
	@Test
	public void testExecute_noSuchCommandAdded() {
		Exception exception = assertThrows(NoSuchCommandAddedException.class, () -> {
			commandList.execute("sayHi");
		});
		
		String expectedMessage = "sayHi";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	@Test
	public void testExecute_incorrectNumberOfParam() {
		commandList.register(MockCommandFactory.get("twoParam"));
		commandList.register(MockCommandFactory.get("oneParam"));
		
		Exception exception = assertThrows(IncorrectNumberOfParamException.class, () -> {
			commandList.execute("twoParam one");
			commandList.execute("oneParam one,two");
		});
		
		String expectedMessage = "command twoParam needs 2 parameter(s). 1 provided instead.";
		String actualMessage = exception.getMessage();
		
		System.out.println(actualMessage);
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
}
