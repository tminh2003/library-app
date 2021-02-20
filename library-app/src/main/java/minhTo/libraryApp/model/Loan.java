package minhTo.libraryApp.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loan_table")
public class Loan {

	@Id @GeneratedValue
	@Column(name = "loan_id")
	private int id;

	@Embedded
	@AttributeOverride(name = "name", column = @Column(name = "patron_name"))
	private Patron patron;

	@Embedded
	@AttributeOverride(name = "title", column = @Column(name = "book_name"))
	private Book book;

	public Loan() {
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public int getId() {
		return id;
	}

	public Book getBook() {
		return book;
	}

	public Patron getPatron() {
		return patron;
	}

}