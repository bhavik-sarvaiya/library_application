package com.library.application.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.application.model.Book;
import com.library.application.model.Borrowed;
import com.library.application.model.User;
import com.library.application.service.LibraryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;

	/*
	 * a) returns all users who have actually borrowed at least one book
	 */
	@GetMapping("/borrowed-users")
	@Operation(summary = "borrowed-users", description = "returns all users who have actually borrowed at least one book",
    responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content),
            @ApiResponse(description = "When something goes wrong", responseCode = "500", content = @Content)
    })
	public List<User> getBorrowedUsers() {
		return libraryService.getBorrowedUsers();
	}

	/*
	 * b) returns all non-terminated users who have not currently borrowed anything
	 */
	@GetMapping("/active-non-borrowers")
	@Operation(summary = "active non borrowers", description = "returns all non-terminated users who have not currently borrowed anything",
    responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content),
            @ApiResponse(description = "When something goes wrong", responseCode = "500", content = @Content)
    })
	public List<User> getActiveNonBorrowers() {
		return libraryService.getActiveNonBorrowers();
	}

	/*
	 * c) returns all users who have borrowed a book on a given date
	 */
	@GetMapping("/users-borrowed-on")
	@Operation(summary = "users borrowed on", description = "returns all users who have borrowed a book on a given date",
    responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content),
            @ApiResponse(description = "When something goes wrong", responseCode = "500", content = @Content)
    })
	public List<User> getUsersBorrowedOn(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return libraryService.getUsersBorrowedOn(date);
	}

	/*
	 * d) returns all books borrowed by a given user in a given date range
	 */
	@GetMapping("/user-borrows")
	@Operation(summary = "user borrows", description = "returns all books borrowed by a given user in a given date range.",
    responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content),
            @ApiResponse(description = "When something goes wrong", responseCode = "500", content = @Content)
    })
	public List<Borrowed> getUserBorrows(@RequestParam("userId") Long userId,
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
			@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
		return libraryService.getUserBorrows(userId, start, end);
	}

	/*
	 * e) returns all available (not borrowed) books
	 */
	@GetMapping("/available-books")
	@Operation(summary = "available books", description = "returns all available (not borrowed) books.",
    responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content),
            @ApiResponse(description = "When something goes wrong", responseCode = "500", content = @Content)
    })
	public List<Book> getAvailableBooks() {
		return libraryService.getAvailableBooks();
	}
}
