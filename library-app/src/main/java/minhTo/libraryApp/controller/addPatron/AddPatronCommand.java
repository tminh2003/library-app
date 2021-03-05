package minhTo.libraryApp.controller.addPatron;

import org.hibernate.SessionFactory;

import commandLine.Command;
import minhTo.libraryApp.DatabaseAware;

public class AddPatronCommand extends Command implements DatabaseAware{
	private SessionFactory sessionFactory;
	
	@Override
	protected void setInfo() {
		alias = "ap";
		param = "patron name, email address";
		desc = "add patron [patron_name] with [email address] into the system";
		DefaultOption option = new DefaultOption();
		option.setDatabaseSessionFactory(sessionFactory);
		optionList.register(option);
	}
	
	public void setDatabaseSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

}
