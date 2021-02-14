package minhTo.libraryApp.controller.checkOutBook;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import commandLine.Option;
import minhTo.libraryApp.controller.DatabaseAware;
import minhTo.libraryApp.model.Book;
import minhTo.libraryApp.model.Loan;
import minhTo.libraryApp.model.Patron;

public class DefaultOption extends Option implements DatabaseAware{

	private SessionFactory sessionFactory;

	/*
	 * 
	 * Check out a book with patron name and book name
	 * Example: execute("John Smith, Harry Potter")
	 */
	public void execute(String param) {
		String patronName;
		String bookName;
		
		Scanner scanner = new Scanner(param);
		scanner.useDelimiter(",");
		
		patronName = scanner.next();
		bookName = scanner.next();
		
		scanner.close();
		
		//Remove extraneous spaces
		while(bookName.charAt(0) == ' ') {
			bookName = bookName.substring(1);
		}
		
		while(bookName.charAt(bookName.length() - 1) == ' ') {
			bookName = bookName.substring(0, bookName.length() - 1);
		}

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Loan loan = new Loan();
		loan.setPatron(new Patron(patronName));
		loan.setBook(new Book(bookName));
		
		session.save(loan);
		transaction.commit();
		session.close();
		
	}

	protected void setInfo() {
		alias = "default";
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}

}
