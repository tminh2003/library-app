package minhTo.libraryApp.controller.checkOutBook;

import org.hibernate.SessionFactory;

import commandLine.Command;
import minhTo.commandLineAddOn.DatabaseAware;

public class CheckOutBookCommand extends Command implements DatabaseAware{
	private SessionFactory sessionFactory;

	protected void setInfo() {
		alias = "o";
		param = "patron name, book name";
		desc = "Check out a book with book name and patron name";
		DefaultOption defaultOption = new DefaultOption();
		defaultOption.setDatabaseSessionFactory(sessionFactory);
		optionList.register(defaultOption);
	}

	public void setDatabaseSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}

}
