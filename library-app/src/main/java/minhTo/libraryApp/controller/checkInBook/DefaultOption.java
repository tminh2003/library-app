package minhTo.libraryApp.controller.checkInBook;

import org.hibernate.SessionFactory;

import commandLine.Option;
import minhTo.libraryApp.DatabaseAware;

public class DefaultOption extends Option implements DatabaseAware{

	private SessionFactory sessionFactory;

	@Override
	protected void setInfo() {
		alias = "default";
	}

	@Override
	public void execute(String arg0) {
		
	}
	
	public void setDatabaseSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}

}
