package minhTo.libraryApp.controller.addBook;

import org.hibernate.SessionFactory;

import commandLine.Command;
import minhTo.commandLineAddOn.DatabaseAware;

public class AddBookCommand extends Command implements DatabaseAware{
	private SessionFactory sessionFactory;

	@Override
	protected void setInfo() {
		alias = "ab";
		param = "title, book name, isbn, cost";
		desc = "add book with the provided info into the database";
		DefaultOption option = new DefaultOption();
		option.setDatabaseSessionFactory(sessionFactory);
		optionList.register(option);
	}
	
	@Override
	public void setDatabaseSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}


}
