package com.library.application.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.application.model.Borrowed;
import com.library.application.model.User;

@Repository
public interface BorrowedRepository extends JpaRepository<Borrowed, Long> {
	public List<Borrowed> findByBorrowerAndBorrowedFromBetween(User user, LocalDate start, LocalDate end);
}
