package minhTo.libraryApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patron_table")
public class Patron {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patron_id")
	private int id;
	
	@Column(name = "patron_name")
	private String name;
	
	@Column(name = "patron_email")
	private String emailAddress;
	
	@Column(name = "fine_balance")
	private double fineBalance;
	
	@Column(name = "num_books_out")
	private int numOfCheckedOutBooks;
	
	public Patron() {}
	
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
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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
	
	public String getEmailAddress() {
		return emailAddress;
	}
}