package minhTo.libraryApp.model;


import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Book {
	
	private String title;
	
	@Transient
	private String author;

	@Transient
	private String isbn;
	

	@Transient
	private double cost;
	

	@Transient
	private String currentStatus;
	
	private Book() {}
	
	public Book(String name) {
		this.title = name;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String setIsbn() {
		return isbn;
	}

	public double getCost() {
		return cost;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}
}