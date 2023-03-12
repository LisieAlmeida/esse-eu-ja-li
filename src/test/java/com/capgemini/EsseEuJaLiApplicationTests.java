package com.capgemini;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.repositories.UserRepository;
import com.capgemini.service.UserService;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
class EsseEuJaLiApplicationTests {

	@Test
	void contextLoads() {
		
	}
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService; 

    /*@BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }*/

    @Test
    void whenValidUserId_thenUserShouldBeFound() {
        // Arrange
        User user = new User("John Doe", "johndoe@example.com", "password");
        userRepository.save(user);

        // Act
        User foundUser = userService.getUserById(user.getId());

        // Assert
        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getName(), foundUser.getName());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getPassword(), foundUser.getPassword());
    }

}
