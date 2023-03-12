package com.capgemini.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.Reading;
import com.capgemini.service.ReadingService;

@RestController
@RequestMapping("/api/readings")
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @PostMapping("/{bookId}")
    public ResponseEntity<Reading> createReading(@PathVariable Long bookId, @RequestBody Reading reading) {
        Reading newReading = readingService.createReading(bookId, reading);
        return new ResponseEntity<>(newReading, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reading> getReadingById(@PathVariable Long id) {
        Reading reading = readingService.getReadingById(id);
        return new ResponseEntity<>(reading, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reading> updateReading(@PathVariable Long id, @RequestBody Reading reading) {
        Reading updatedReading = readingService.updateReading(id, reading);
        return new ResponseEntity<>(updatedReading, HttpStatus.OK);
    }
    
    @PostMapping("/read/{id}")
    public ResponseEntity<Reading> markAsRead(@PathVariable(value = "id") Long readingId) {
        Reading reading = readingService.getReadingById(readingId);
        if (reading == null) {
            return ResponseEntity.notFound().build();
        }
        reading.setPagesRead(reading.getBook().getPageCount());
        readingService.updateReading(readingId, reading);
        return ResponseEntity.ok().body(reading);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReading(@PathVariable Long id) {
        readingService.deleteReading(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}