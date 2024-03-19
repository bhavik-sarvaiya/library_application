package com.library.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.library.application.service.LibraryService;
import com.library.application.util.Constants;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	private final LibraryService libraryService;

	public LibraryApplication(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		libraryService.uploadUsers(Constants.USER_FILE_PATH);
		libraryService.uploadBorrowedData(Constants.BORROEWD_FILE_PATH);
		libraryService.uploadBooksData(Constants.BOOKS_FILE_PATH);
	}

}
