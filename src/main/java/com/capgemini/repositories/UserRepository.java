package com.capgemini.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // adicione um método para buscar usuários pelo nome
    List<User> findByNameContaining(String name);
    
    // adicione um método para buscar usuários pelo email
    User findByEmail(String email);
}