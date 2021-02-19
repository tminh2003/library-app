package minhTo.libraryApp.controller.checkInBook;

import org.hibernate.SessionFactory;

import commandLine.Command;
import minhTo.libraryApp.DatabaseAware;

public class CheckInBookCommand extends Command implements DatabaseAware{
	private SessionFactory sessionFactory;
	
	@Override
	protected void setInfo() {
		alias = "i";
		param = "patron name, book name";
		desc = "Check in book [book name] borrowed by patron [patron name]";
		optionList.register(new DefaultOption());
	}
	
	public void setDatabaseSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}

	

}
