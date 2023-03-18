package com.capgemini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.capgemini.controllers.BookController;
import com.capgemini.controllers.UserController;
import com.capgemini.repositories.BookRepository;
import com.capgemini.service.BookService;
import com.capgemini.service.UserService;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class BookTests {
	@Test
	void contextLoads() {
		
	}

    @Autowired
    private BookController bookController;

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookService bookService;
    
  

    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setTitle("The Lord of the Rings");
        book.setAuthor("J.R.R. Tolkien");
        book.setGenre(GenrEnum.FANTASY);
        book.setPageCount(100);

        bookController.createBook(book);

        Book savedBook = bookService.getBookById(book.getId());
        assertEquals("The Lord of the Rings", savedBook.getTitle());
        assertEquals("J.R.R. Tolkien", savedBook.getAuthor());
        assertEquals(GenrEnum.FANTASY, savedBook.getGenre());
        assertEquals("100", String.valueOf(savedBook.getPageCount()));
        
    }


    @Test
    public void testGetAllBooks() {
        Book book1 = new Book();
        book1.setTitle("Harry Potter and the Philosopher's Stone");
        book1.setAuthor("J.K. Rowling");
        book1.setGenre(GenrEnum.FANTASY);
        book1.setPageCount(124);

        Book book2 = new Book();
        book2.setTitle("To Kill a Mockingbird");
        book2.setAuthor("Harper Lee");
        book2.setGenre(GenrEnum.ROMANCE);
        book2.setPageCount(345);
        
        bookController.createBook(book1);
        bookController.createBook(book2);

       // bookRepository.saveAll(List.of(book1, book2));

        /*ResponseEntity<List<Book>> response = bookController.getAllBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());*/

        List<Book> returnedBooks = bookService.getAllBooks();
        assertEquals(10, returnedBooks.size());
        //assertTrue(returnedBooks.contains(book1));
       // assertTrue(returnedBooks.contains(book2));
       
        
        
        
    }
}
