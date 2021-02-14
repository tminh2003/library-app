package controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import minhTo.libraryApp.model.Loan;

public class SessionFactoryMockInjector {
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addAnnotatedClass(Loan.class);
		
		sessionFactory = configuration.buildSessionFactory();
		
		return sessionFactory;
		
	}
	
}
