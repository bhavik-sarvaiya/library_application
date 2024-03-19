package com.library.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.application.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	@Query("SELECT b FROM Book b WHERE b NOT IN (SELECT br.book FROM Borrowed br WHERE br.borrowedTo IS NULL)")
	public List<Book> findAvailableBooks();

	public Book findByTitle(String title);
}
