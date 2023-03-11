package com.capgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.capgemini.Genre;


public interface GenreRepository extends JpaRepository<Genre, Long> {

}