import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.myapps.libraryapp_db.LibraryAppDatabasesApplication;

@SpringBootTest
@ContextConfiguration(classes = LibraryAppDatabasesApplication.class)
public class LibraryAppDatabasesApplicationTest {

	@Test
	public void contextLoads() {
	}

}
