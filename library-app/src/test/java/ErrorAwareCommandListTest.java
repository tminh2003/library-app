import org.junit.BeforeClass;
import org.junit.Test;

import minhTo.libraryApp.ErrorAwareCommandList;
import minhTo.libraryApp.exception.NoSuchCommandAddedException;
import minhTo.libraryApp.exception.IncorrectNumberOfParamException;

public class ErrorAwareCommandListTest {
	private static ErrorAwareCommandList commandList;
	
	@BeforeClass
	public static void setUp() {
		commandList = new ErrorAwareCommandList();
	}
	
	@Test(expected = NoSuchCommandAddedException.class)
	public void testExecute_noSuchCommandAdded() {
		commandList.execute("sayHi");
	}
	
	@Test(expected = IncorrectNumberOfParamException.class)
	public void testExecute_incorrectNumberOfParam() {
		commandList.register(MockCommandFactory.get("twoParam"));
		commandList.execute("twoParam one");
	}
}
