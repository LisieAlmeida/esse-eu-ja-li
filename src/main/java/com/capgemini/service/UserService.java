package com.capgemini.service;



import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.capgemini.User;
import com.capgemini.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Validações, lógica de negócio, etc.
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        // Validações, lógica de negócio, etc.
        return userRepository.save(user);
    }

    public User getUserById(@PathVariable Long id) {
    	return userRepository.findById(id)
    	        .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }
    

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
