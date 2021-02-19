package minhTo.libraryApp;

import org.hibernate.SessionFactory;

public interface DatabaseAware {
	public void setDatabaseSessionFactory(SessionFactory sessionFactory);
}
