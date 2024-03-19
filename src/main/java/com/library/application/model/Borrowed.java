package com.library.application.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Borrowed {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private User borrower;

	@ManyToOne
	private Book book;

	public Borrowed() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Borrowed(Long id, User borrower, Book book, LocalDate borrowedFrom, LocalDate borrowedTo) {
		super();
		this.id = id;
		this.borrower = borrower;
		this.book = book;
		this.borrowedFrom = borrowedFrom;
		this.borrowedTo = borrowedTo;
	}

	private LocalDate borrowedFrom;
	private LocalDate borrowedTo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getBorrower() {
		return borrower;
	}

	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDate getBorrowedFrom() {
		return borrowedFrom;
	}

	public void setBorrowedFrom(LocalDate borrowedFrom) {
		this.borrowedFrom = borrowedFrom;
	}

	public LocalDate getBorrowedTo() {
		return borrowedTo;
	}

	public void setBorrowedTo(LocalDate borrowedTo) {
		this.borrowedTo = borrowedTo;
	}

}
