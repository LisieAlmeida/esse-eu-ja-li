package com.capgemini.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByGenre(String genre);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

}