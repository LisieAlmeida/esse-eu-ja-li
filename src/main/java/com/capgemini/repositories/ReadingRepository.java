package com.capgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.capgemini.Reading;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
}