package com.capgemini.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(long id);
   
}