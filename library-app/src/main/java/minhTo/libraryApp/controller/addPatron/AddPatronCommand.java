package minhTo.libraryApp.controller.addPatron;

import org.hibernate.SessionFactory;

import commandLine.Command;
import minhTo.libraryApp.DatabaseAware;

public class AddPatronCommand extends Command implements DatabaseAware{
	private SessionFactory sessionFactory;
	
	@Override
	protected void setInfo() {
		alias = "ap";
		param = "patron name";
		desc = "add patron [patron_name] into the system";
		DefaultOption option = new DefaultOption();
		option.setDatabaseSessionFactory(sessionFactory);
		optionList.register(new DefaultOption());
	}
	
	public void setDatabaseSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

}
