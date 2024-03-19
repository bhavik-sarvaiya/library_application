package com.library.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.application.model.Book;
import com.library.application.model.User;
import com.library.application.repository.BookRepository;
import com.library.application.repository.BorrowedRepository;
import com.library.application.repository.UserRepository;
import com.library.application.util.Constants;

@SpringBootTest
public class LibraryServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowedRepository borrowedRepository;

    @InjectMocks
    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        libraryService = new LibraryService();
    }

    @Test
    public void testGetBorrowedUsers() {
        List<User> mockUsers = Arrays.asList(new User(), new User());
        when(userRepository.findByMemberTillIsNotNullAndBorrowsIsNotEmpty()).thenReturn(mockUsers);

        List<User> users = libraryService.getBorrowedUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findByMemberTillIsNotNullAndBorrowsIsNotEmpty();
    }

    @Test
    public void testUploadBooksData() {
        String filePath = Constants.BOOKS_FILE_PATH;
        libraryService.uploadBooksData(filePath);

        verify(bookRepository, atLeastOnce()).save(any(Book.class));
    }
    
    @Test
    public void testUploadUsers() throws Exception {
        String filePath = Constants.USER_FILE_PATH;
        libraryService.uploadUsers(filePath);

        verify(userRepository, atLeastOnce()).save(any(User.class));
    }

}
