package com.myapps.library_app_shared.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User{

	private @Id String name;
	private String emailAddress;
	private String password;
	private String authority;
	private double fineBalance;
	private int numCheckedOutBooks;

	private User() {
	}

	public User(String name, String emailAddress, String password, String authority) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.password = password;
		this.authority = authority;
		this.fineBalance = 0;
		this.numCheckedOutBooks = 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFineBalance(double fineBalance) {
		this.fineBalance = fineBalance;
	}

	public void setNumOfCheckedOutUsers(int numCheckedOutBooks) {
		this.numCheckedOutBooks = numCheckedOutBooks;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setNumCheckedOutBooks(int numCheckedOutBooks) {
		this.numCheckedOutBooks = numCheckedOutBooks;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public String getName() {
		return name;
	}

	public double getFineBalance() {
		return fineBalance;
	}

	public int getNumOfCheckedOutUsers() {
		return numCheckedOutBooks;
	}

	public String getAuthority() {
		return authority;
	}

	public int getNumCheckedOutBooks() {
		return numCheckedOutBooks;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getPassword() {
		return password;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(this.name, user.name) && Objects.equals(this.password, user.password)
				&& Objects.equals(this.emailAddress, user.emailAddress)
				&& Objects.equals(this.authority, user.authority) && Objects.equals(this.fineBalance, user.fineBalance)
				&& Objects.equals(this.numCheckedOutBooks, user.numCheckedOutBooks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.password, this.emailAddress, this.authority, this.fineBalance,
				this.numCheckedOutBooks);
	}

	@Override
	public String toString() {
		return "User{" + "name='" + this.name + '\'' + ", password='" + this.password + '\'' + ", emailAddress='"
				+ this.emailAddress + '\'' + ", authority='" + this.authority + '\'' + ", fineBalance='"
				+ this.fineBalance + '\'' + ", numCheckedOutBooks='" + this.numCheckedOutBooks + '\'' + '}';
	}

	
}