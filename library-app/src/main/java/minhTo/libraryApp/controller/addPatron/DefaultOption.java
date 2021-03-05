package minhTo.libraryApp.controller.addPatron;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import commandLine.Option;
import minhTo.libraryApp.DatabaseAware;
import minhTo.libraryApp.model.Patron;

public class DefaultOption extends Option implements DatabaseAware{
	private SessionFactory sessionFactory;
	
	@Override
	protected void setInfo() {
		alias = "default";
	}
	
	@Override
	public void execute(String arg0) {
		String patronName;
		String patronEmail;
		
		Scanner scanner = new Scanner(arg0);
		scanner.useDelimiter(",");
		
		patronName = scanner.next();
		patronEmail = scanner.next();
		
		scanner.close();
		
		//Remove extraneous spaces
		while(patronEmail.charAt(0) == ' ') {
			patronEmail = patronEmail.substring(1);
		}
				
		while(patronEmail.charAt(patronEmail.length() - 1) == ' ') {
			patronEmail = patronEmail.substring(0, patronEmail.length() - 1);
		}
		
		System.out.println(patronName);
		System.out.println(patronEmail);
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Patron patron = new Patron();
		patron.setName(patronName);
		patron.setEmailAddress(patronEmail);
		
		try {
			session.save(patron);
		}catch(ConstraintViolationException exception) {
			System.out.println("This email account is already linked to another patron: " + patronEmail);
		}
			
		transaction.commit();
		session.close();
		
	}
	
	public void setDatabaseSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

}
