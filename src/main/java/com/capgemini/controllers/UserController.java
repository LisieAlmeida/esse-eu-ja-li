package com.capgemini.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.User;
import com.capgemini.service.InvalidRequestException;
import com.capgemini.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

   @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
    
  
    @PostMapping("/{userId}/addPoints/{bookId}")
    public User addPoints(@PathVariable Long userId, @PathVariable Long bookId) {
        return userService.addPoints(userId, bookId);
    }
    
    @PostMapping("/{userId}/books/{bookId}/mark-as-read")
    public ResponseEntity<?> markAsRead(@PathVariable Long userId, @PathVariable Long bookId) {
        try {
            User user = userService.markAsRead(userId, bookId);
            return ResponseEntity.ok(user);
        } catch (InvalidRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

}

    

