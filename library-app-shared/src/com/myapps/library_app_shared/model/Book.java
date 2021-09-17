package com.myapps.library_app_shared.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book{

	private @Id @GeneratedValue Long id;
	private String title;
	private String author;
	private String isbn;
	private double cost;
	private String currentStatus;
	
	private Book() {}
	
	public Book(String title, String author, String isbn, double cost) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.cost = cost;
		this.currentStatus = "IN";
	}
	
	public void setId(Long id) {
		this.id = id;
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

	public Long getId() {
		return id;
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
	
	@Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Book))
	      return false;
	    Book book = (Book) o;
	    return Objects.equals(this.id, book.id) && Objects.equals(this.title, book.title)
	        && Objects.equals(this.author, book.author) && Objects.equals(this.isbn, book.isbn)
	        && Objects.equals(this.cost, book.cost) && Objects.equals(this.currentStatus, book.currentStatus);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(this.id, this.title, this.author, this.isbn, this.cost, this.currentStatus);
	  }

	  @Override
	  public String toString() {
	    return "Book{" + "id=" + this.id 
	    		+ ", title='" + this.title + '\'' 
	    		+ ", author='" + this.author + '\''
	    		+ ", isbn='" + this.isbn + '\''
	    		+ ", cost='" + this.cost + '\''
	    		+ ", currentStatus='" + this.currentStatus + '\'' 
	    		+ '}';
	  }
}