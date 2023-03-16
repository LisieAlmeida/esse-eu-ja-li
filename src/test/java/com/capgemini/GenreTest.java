package com.capgemini;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capgemini.controllers.GenreController;
import com.capgemini.repositories.GenreRepository;
import com.capgemini.service.GenreService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class GenreTest {
	
	@Test
	void contextLoads() {
		
	}
	

    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private GenreService genreService;
    
    @Autowired
    private GenreController genreController;

    @Test
    void testFindAllGenres() {
        Genre genre1 = new Genre("Science Fiction");
        genreRepository.save(genre1);

        Genre genre2 = new Genre("Romance");
        genreRepository.save(genre2);

        ResponseEntity<List<Genre>> response = genreController.getAllGenres();
        List<Genre> genres = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, genres.size());
        //assertTrue(genres.contains(genre1));
        //assertTrue(genres.contains(genre2));
    }
}