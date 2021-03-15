package testUtil;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import minhTo.libraryApp.model.Patron;

public class SessionFactoryMockInjector {
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addAnnotatedClass(Patron.class);
		
		sessionFactory = configuration.buildSessionFactory();
		
		return sessionFactory;
		
	}
	
}
