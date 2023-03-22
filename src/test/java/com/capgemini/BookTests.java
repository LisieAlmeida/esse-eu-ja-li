package com.capgemini;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.controllers.BookController;
import com.capgemini.controllers.UserController;
import com.capgemini.repositories.BookRepository;
import com.capgemini.service.BookService;
import com.capgemini.service.UserService;

import jakarta.transaction.Transactional;

@RunWith(SpringRunner.class)

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
        book.setTitle("Opções de um Feno");
        book.setAuthor("Jennifer Vaca");
        book.setGenre(GenrEnum.ROMANCE);
        book.setPageCount(100);

        bookController.createBook(book);

        Book savedBook = bookService.getBookById(book.getId());
        assertEquals("Opções de um Feno", savedBook.getTitle());
        assertEquals("Jennifer Vaca", savedBook.getAuthor());
        assertEquals(GenrEnum.ROMANCE, savedBook.getGenre());
        assertEquals("100", String.valueOf(savedBook.getPageCount()));
        
    }


    @Test
    public void testGetAllBooks() {
        Book book1 = new Book();
        book1.setTitle("Loucuras de um Gafanhoto");
        book1.setAuthor("José Esperança");
        book1.setGenre(GenrEnum.COMÉDIA);
        book1.setPageCount(124);

        Book book2 = new Book();
        book2.setTitle("Escola para Desaprender");
        book2.setAuthor("Antônio Letrado");
        book2.setGenre(GenrEnum.TERROR);
        book2.setPageCount(122);
        
        Book book3 = new Book();
        book3.setTitle("A saída sem entrada");
        book3.setAuthor("Manoel Perdido");
        book3.setGenre(GenrEnum.TERROR);
        book3.setPageCount(345);
        
        Book book4 = new Book();
        book4.setTitle("O homem dos desejos");
        book4.setAuthor("Maria Gênio");
        book4.setGenre(GenrEnum.TERROR);
        book4.setPageCount(454);
        
        Book book5 = new Book();
        book5.setTitle("Um mundo invertido");
        book5.setAuthor("Fernanda Jupter");
        book5.setGenre(GenrEnum.FICÇÃO);
        book5.setPageCount(225);
        
        Book book6 = new Book();
        book6.setTitle("A saída sem entrada");
        book6.setAuthor("Manoel Perdido");
        book6.setGenre(GenrEnum.TERROR);
        book6.setPageCount(198);
        
        Book book7 = new Book();
        book7.setTitle("Hamburguer com Sentimentos");
        book7.setAuthor("João Faminto");
        book7.setGenre(GenrEnum.ROMANCE);
        book7.setPageCount(321);
        
        Book book8 = new Book();
        book8.setTitle("Chicletes para Viver");
        book8.setAuthor("Benedito Goma");
        book8.setGenre(GenrEnum.ROMANCE);
        book8.setPageCount(356);
        
        Book book9 = new Book();
        book9.setTitle("Um Sonho de Verdade");
        book9.setAuthor("Marta Sonífero");
        book9.setGenre(GenrEnum.COMÉDIA);
        book9.setPageCount(512);
        
        Book book10 = new Book();
        book10.setTitle("Calças no Mundo da Lua");
        book10.setAuthor("Gabriela Astral");
        book10.setGenre(GenrEnum.TERROR);
        book10.setPageCount(620);
        
        Book book11 = new Book();
        book11.setTitle("O Show de um Tamanco");
        book11.setAuthor("Carmem Salto");
        book11.setGenre(GenrEnum.ROMANCE);
        book11.setPageCount(229);
        
        Book book12 = new Book();
        book12.setTitle("As 7 Vidas de um Gato");
        book12.setAuthor("Esmeralda Felino");
        book12.setGenre(GenrEnum.ROMANCE);
        book12.setPageCount(479);
        
        
        
        bookController.createBook(book1);
        bookController.createBook(book2);
        bookController.createBook(book3);
        bookController.createBook(book4);
        bookController.createBook(book5);
        bookController.createBook(book6);
        bookController.createBook(book7);
        bookController.createBook(book8);
        bookController.createBook(book9);
        bookController.createBook(book10);
        bookController.createBook(book11);
        bookController.createBook(book12);

       // bookRepository.saveAll(List.of(book1, book2));

        /*ResponseEntity<List<Book>> response = bookController.getAllBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());*/

        List<Book> returnedBooks = bookService.getAllBooks();
        assertEquals(12, returnedBooks.size());
        //assertTrue(returnedBooks.contains(book1));
       // assertTrue(returnedBooks.contains(book2));
       
        
        
        
    }
}
