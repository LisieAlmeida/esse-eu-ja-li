package com.capgemini;

import static org.junit.Assert.assertEquals;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.service.BookService;
import com.capgemini.service.ReadingService;
import com.capgemini.service.UserService;

import jakarta.transaction.Transactional;

//@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReadingTests {

    @Autowired
    private ReadingService readingService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    private User user;
    private Book book;

   

    @Test
    public void testCreateReading() {
    	 user = new User("John Doe", "john.doe@example.com", "password");
         user = userService.createUser(user);

         book = new Book("Test Book", "Test Author", 100, GenrEnum.COMÉDIA);
         book = bookService.createBook(book);
    	
        Reading reading = new Reading(user, book);
        reading.setPagesRead(book.getPageCount());
        readingService.createReading(book.getId(), reading);

        Reading savedReading = readingService.getReadingById(reading.getId());
        assertEquals(user, savedReading.getUser());
        assertEquals(book, savedReading.getBook());
        assertEquals(100, savedReading.getPagesRead());
    }
}
