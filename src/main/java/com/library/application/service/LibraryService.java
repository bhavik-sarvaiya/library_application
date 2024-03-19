package com.library.application.service;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.application.model.Book;
import com.library.application.model.Borrowed;
import com.library.application.model.User;
import com.library.application.repository.BookRepository;
import com.library.application.repository.BorrowedRepository;
import com.library.application.repository.UserRepository;

@Service
public class LibraryService {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BorrowedRepository borrowedRepository;

	public List<User> getBorrowedUsers() {
		// TODO Auto-generated method stub
		return userRepository.findByMemberTillIsNotNullAndBorrowsIsNotEmpty();
	}

	public List<User> getActiveNonBorrowers() {
		// TODO Auto-generated method stub
		return userRepository.findByMemberTillIsNullAndBorrowsIsEmpty();
	}

	public List<User> getUsersBorrowedOn(LocalDate date) {
		// TODO Auto-generated method stub
		return userRepository.findByBorrowsBorrowedFrom(date);
	}

	public List<Borrowed> getUserBorrows(Long userId, LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
		return borrowedRepository.findByBorrowerAndBorrowedFromBetween(userRepository.findById(userId).get(), start,
				end);
	}

	public List<Book> getAvailableBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAvailableBooks();
	}

	public void uploadUsers(String filePath) {
		try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			for (CSVRecord record : parser) {
				String name = record.get("Name");
				String firstName = record.get("First name");
				LocalDate memberSince = null;
				LocalDate memberTill = null;

				String memberSinceStr = record.get("Member since");
				if (memberSinceStr != null && !memberSinceStr.isEmpty()) {
					memberSince = LocalDate.parse(memberSinceStr, formatter);
				}

				String memberTillStr = record.get("Member till");
				if (memberTillStr != null && !memberTillStr.isEmpty()) {
					memberTill = LocalDate.parse(memberTillStr, formatter);
				}

				// Check if user already exists
				boolean userExists = userRepository.existsByNameAndFirstNameAndMemberSince(name, firstName,
						memberSince);

				if (!userExists) {
					User user = new User();
					user.setName(name);
					user.setFirstName(firstName);
					user.setMemberSince(memberSince);
					user.setMemberTill(memberTill);
					user.setGender(record.get("Gender"));
					userRepository.save(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions properly
		}
	}

	public void uploadBorrowedData(String filePath) {
		try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			for (CSVRecord record : parser) {
				Borrowed borrowed = new Borrowed();

				// Assuming the Borrower column has names in the format "Lastname,Firstname"
				String[] nameParts = record.get("Borrower").split(",");
				String lastName = nameParts[0].trim();
				String firstName = nameParts.length > 1 ? nameParts[1].trim() : "";

				// Find the user by last name and first name
				User user = userRepository.findByNameAndFirstName(lastName, firstName);

				// Assuming the Book column contains the book title
				String bookTitle = record.get("Book");
				Book book = bookRepository.findByTitle(bookTitle);

				borrowed.setBorrower(user);
				borrowed.setBook(book);
				borrowed.setBorrowedFrom(LocalDate.parse(record.get("borrowed from"), formatter));
				borrowed.setBorrowedTo(LocalDate.parse(record.get("borrowed to"), formatter));

				borrowedRepository.save(borrowed);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions properly
		}
	}

	public void uploadBooksData(String filePath) {
		try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			for (CSVRecord record : parser) {
				Book book = new Book();
				book.setTitle(record.get("Title"));
				book.setAuthor(record.get("Author"));
				book.setGenre(record.get("Genre"));
				book.setPublisher(record.get("Publisher"));

				bookRepository.save(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Proper exception handling
		}
	}
}
