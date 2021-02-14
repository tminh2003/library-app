package minhTo.libraryApp.controller;

import org.hibernate.SessionFactory;

public interface DatabaseAware {
	public void setSessionFactory(SessionFactory sessionFactory);
}
