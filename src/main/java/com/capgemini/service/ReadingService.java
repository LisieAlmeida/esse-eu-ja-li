package com.capgemini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.Book;
import com.capgemini.Reading;
import com.capgemini.ResourceNotFoundException;
import com.capgemini.repositories.BookRepository;
import com.capgemini.repositories.ReadingRepository;

import jakarta.transaction.Transactional;

//@Transactional
@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private BookRepository bookRepository;

    public Reading createReading(Long bookId, Reading reading) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
    
        reading.setBook(book);
        return readingRepository.save(reading);
    };

    public Reading getReadingById(Long id) {
        return readingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found with id " + id));
    }

    public Reading updateReading(Long id, Reading updatedReading) {
        Reading existingReading = readingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found with id " + id));
        existingReading.setDate(updatedReading.getDate());
        //existingReading.setDate(updatedReading.getDate());
        existingReading.setPagesRead(updatedReading.getPagesRead());
        return readingRepository.save(existingReading);
    }
    
    public void updatePagesRead(Long readingId) throws NotFoundException {
        Reading reading = readingRepository.findById(readingId)
        		.orElseThrow(() ->new ResourceNotFoundException("Not found with id " + readingId));
        Book book = bookRepository.findById(reading.getBook().getId())
        		.orElseThrow(() -> new ResourceNotFoundException("Not found with id " + readingId));
        reading.setPagesRead(book.getPageCount());
        readingRepository.save(reading);
    }
    
    

    public void deleteReading(Long id) {
        Reading reading = readingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found with id " + id));
        readingRepository.delete(reading);
    }
}