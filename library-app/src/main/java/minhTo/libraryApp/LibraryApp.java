package minhTo.libraryApp;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import commandLine.CommandList;
import minhTo.commandLineAddOn.ErrorAwareCommandList;
import minhTo.libraryApp.controller.checkOutBook.CheckOutBookCommand;
import minhTo.libraryApp.exception.NoSuchCommandAddedException;

public class LibraryApp {
	
	public static void main(String[]args) {
		SessionFactory sessionFactory = null;
		
		CommandList commandList = new ErrorAwareCommandList();
		CheckOutBookCommand checkOutBook = new CheckOutBookCommand();
		checkOutBook.setDatabaseSessionFactory(sessionFactory);
		
		
		commandList.register(checkOutBook);

		System.out.println("Welcome to the library app. Enter \'exit\' to quit. "
				+ "Enter \'help\' for a list of commands");
		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
		while(!input.equals("exit")) {
			commandList.execute(input);
			input = scanner.nextLine();
		}
		
	}
	
}
