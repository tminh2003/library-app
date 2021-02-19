package minhTo.libraryApp.controller.addPatron;

import java.util.Scanner;

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
	}
	
	public void setDatabaseSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

}
