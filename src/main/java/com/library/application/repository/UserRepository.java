package com.library.application.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.application.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public boolean existsByNameAndFirstNameAndMemberSince(String name, String firstName, LocalDate memberSince);

	public User findByNameAndFirstName(String name, String firstName);

	public List<User> findByMemberTillIsNullAndBorrowsIsEmpty();

	public List<User> findByMemberTillIsNotNullAndBorrowsIsNotEmpty();

	List<User> findByBorrowsBorrowedFrom(LocalDate date);

}