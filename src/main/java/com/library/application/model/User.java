package com.library.application.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "firstName", "memberSince" }) })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String firstName;
	private LocalDate memberSince;
	private LocalDate memberTill;
	private String gender;

	@OneToMany(mappedBy = "borrower")
	private Set<Borrowed> borrows = new HashSet<>();

	public User() {
	}

	public User(Long id, String name, String firstName, LocalDate memberSince, LocalDate memberTill, String gender) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.memberSince = memberSince;
		this.memberTill = memberTill;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public LocalDate getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(LocalDate memberSince) {
		this.memberSince = memberSince;
	}

	public LocalDate getMemberTill() {
		return memberTill;
	}

	public void setMemberTill(LocalDate memberTill) {
		this.memberTill = memberTill;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
