package com.myapps.library_app_shared.model;

public interface IUser {
	
	public String getName();
	public double getFineBalance();
	public int getNumOfCheckedOutUsers();
	public String getAuthority();
	public int getNumCheckedOutBooks();
	public String getEmailAddress();
	public String getHashedPassword();
	
}