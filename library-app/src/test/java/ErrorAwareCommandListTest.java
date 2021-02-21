import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import minhTo.libraryApp.ErrorAwareCommandList;
import minhTo.libraryApp.exception.NoSuchCommandAddedException;

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
}
