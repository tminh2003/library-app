package minhTo.libraryApp.controller.addBook;

import org.hibernate.SessionFactory;

import commandLine.Option;
import minhTo.commandLineAddOn.DatabaseAware;

public class DefaultOption extends Option implements DatabaseAware{
	private SessionFactory sessionFactory;
	
	@Override
	protected void setInfo() {
		alias = "default";
	}
	
	@Override
	public void execute(String arg0) {
		System.out.println("here");
	}
	
	@Override
	public void setDatabaseSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

	

}
