package com.capgemini.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.Book;
import com.capgemini.repositories.BookRepository;
import com.capgemini.service.BookService;


@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable(value = "id") Long bookId) {
       return bookService.getBookById(bookId);   
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable(value = "id") Long bookId, @RequestBody Book bookDetails) {
        return bookService.updateBook(bookId, bookDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable(value = "id") Long bookId) {
      bookService.deleteBook(bookId);
    }
}
