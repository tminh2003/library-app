package commandLineAddOn;
import org.junit.BeforeClass;
import org.junit.Test;
import minhTo.libraryApp.DatabaseAwareCommandList;;

public class TestDatabaseAwareCommandList {
	private DatabaseAwareCommandList commandList;
	
	@BeforeClass
	public void setUp() {
		commandList = new DatabaseAwareCommandList();
	}
	
	@Test
	public void testDefault(){
	}
}
