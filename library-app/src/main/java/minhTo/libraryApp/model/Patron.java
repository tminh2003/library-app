package minhTo.libraryApp.model;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Patron {

	private String name;
	
	@Transient
	private double fineBalance;
	
	@Transient
	private int numOfCheckedOutBooks;
	
	private Patron() {}
	
	public Patron(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFineBalance(double fineBalance) {
		this.fineBalance = fineBalance;
	}

	public void setNumOfCheckedOutBooks(int numOfCheckedOutBooks) {
		this.numOfCheckedOutBooks = numOfCheckedOutBooks;
	}

	public String getName() {
		return name;
	}

	public double getFineBalance() {
		return fineBalance;
	}

	public int getNumOfCheckedOutBooks() {
		return numOfCheckedOutBooks;
	}
}