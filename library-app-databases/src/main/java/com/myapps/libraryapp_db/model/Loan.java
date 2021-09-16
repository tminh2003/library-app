package com.myapps.libraryapp_db.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Loan {
	
	@Id @GeneratedValue
	private Long id;
	private Long bookId;
	private Long userId;
	private Date dueDate;
	private boolean isOverdue;
	
	private Loan() {}
	
	public Loan(Long bookId, Long userId, Date dueDate) {
		this.bookId = bookId;
		this.userId = userId;
		this.dueDate = dueDate;
		this.isOverdue = false;
	}
	
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public boolean isOverdue() {
		return isOverdue;
	}
	public void setOverdue(boolean isOverdue) {
		this.isOverdue = isOverdue;
	}
	
	
}
