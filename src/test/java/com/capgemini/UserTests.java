package com.capgemini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.controllers.UserController;
import com.capgemini.repositories.BookRepository;
import com.capgemini.repositories.UserRepository;
import com.capgemini.service.UserService;

import jakarta.transaction.Transactional;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTests {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserController userController;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testGetAllUsers() {
        User user1 = new User("John", "john@example.com", "password");
        userRepository.save(user1);

        User user2 = new User("Jane", "jane@example.com", "password");
        userRepository.save(user2);

        List<User> users = userService.getAllUsers();

        assertEquals(22, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }
    
    @Test
    public void testGetUserById() {
        User user1 = new User("John", "john@example.com", "password");
        userRepository.save(user1);

        User user2 = new User("Jane", "jane@example.com", "password");
        userRepository.save(user2);

        User foundUser = userService.getUserById(user1.getId());

        assertEquals(user1, foundUser);
    }
    
    @Test
    public void testCreateUser() {
        User user = new User("John", "john@example.com", "password");
        User createdUser = userService.createUser(user);

        assertEquals(user, createdUser);
    }
    
    @Test
    public void testDeleteById() {
        User user = new User("John", "john@example.com", "password");
        userRepository.save(user);

        userService.deleteById(user.getId());

        assertFalse(userRepository.existsById(user.getId()));
    }
    
    @Test
    public void testAddPoints() {
        User user = new User("John", "john@example.com", "password");
        userRepository.save(user);

        Book book = new Book("Book title", "Book author", 200, GenrEnum.COMÉDIA);
        bookRepository.save(book);

        User updatedUser = userService.addPoints(user.getId(), book.getId());

        assertEquals(3, updatedUser.getPoints());
        assertTrue(updatedUser.getBooks().contains(book));
    }
    
    @Test
    public void testUpdateUser() {
        User user = new User("John", "john@example.com", "password");
        userRepository.save(user);

        User updatedUser = new User("John Doe", "johndoe@example.com", "newpassword");
        userService.updateUser(user.getId(), updatedUser);

        User foundUser = userRepository.findById(user.getId()).get();
        assertEquals(updatedUser.getName(), foundUser.getName());
        assertEquals(updatedUser.getEmail(), foundUser.getEmail());
        assertEquals(updatedUser.getPassword(), foundUser.getPassword());
    }
    
    @Test
    void testMarkAsRead() throws Exception {
        // Cria um usuário
    	User user = new User("John", "john@example.com", "password");
        userRepository.save(user);


        // Cria um livro
        Book book = new Book("Book title", "Book author", 200, GenrEnum.COMÉDIA);
        bookRepository.save(book);

        // Adiciona o livro à lista de livros do usuário
        Set<Book> books = new HashSet<>();
        books.add(book);
        user.setBooks(books);

        // Salva o usuário no banco de dados
        userService.createUser(user);

        // Marca o livro como lido
        userController.markAsRead(user.getId(), book.getId());

        // Verifica se o livro foi marcado como lido
        User updatedUser = userService.getUserById(user.getId());
        assertEquals(1, updatedUser.getReadings().size());
        assertEquals(book.getId(), updatedUser.getReadings().iterator().next().getBook().getId());
    }
    
    @Test
    public void testVerifyTrophies()  {
        // Setup
    	User user = new User("John", "john@example.com", "password");
        userRepository.save(user);
        

        Book book1 = new Book("Book title", "Book author", 200, GenrEnum.COMÉDIA);
        bookRepository.save(book1);
       
        Book book2 = new Book("Book title1", "Book author1", 300, GenrEnum.COMÉDIA);
        bookRepository.save(book2);
       
        Book book3 = new Book("Book title2", "Book author2", 100, GenrEnum.COMÉDIA);
        bookRepository.save(book3);
      
        Book book4 = new Book("Book title3", "Book author3", 100, GenrEnum.COMÉDIA);
        bookRepository.save(book4);
        
        Book book5 = new Book("Book title4", "Book author4", 100, GenrEnum.COMÉDIA);
        bookRepository.save(book5);
      
        
        user.addBook(book1);
        user.addBook(book2);
        user.addBook(book3);
        user.addBook(book4);
        user.addBook(book5);
        
        
        Trophy trophy = new Trophy();
        String trophyName = trophy.addGenreTrophy(GenrEnum.COMÉDIA);
        assertFalse(user.hasTrophy(trophyName));
        Book book6 = new Book("Book title6", "Book author6", 100, GenrEnum.COMÉDIA);
        bookRepository.save(book6);
        userService.markAsRead(user.getId(), book6.getId());
        
        
        List<Book> allBooks = new ArrayList<>();
        allBooks.add(book1);
        allBooks.add(book2);
        allBooks.add(book3);
        allBooks.add(book4);
        allBooks.add(book5);
        allBooks.add(book6);
        
        assertEquals("Leitor de COMÉDIA", trophyName );
        assertEquals(user.getBooks().size(), allBooks.size());
        assertEquals(1,user.getTrophies().size());
        
        //userService.markAsRead(user.getId(), book6.getId());
        assertTrue(user.hasTrophy(trophyName));
        
        
        
        

        /*Reading reading1 = new Reading(user, book);
        Reading reading2 = new Reading(user, book1);
        Reading reading3 = new Reading(user, book2);
        Reading reading4 = new Reading(user, book3);
        Reading reading5 = new Reading(user, book4);
        Set<Reading> readings = new HashSet<>();
        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading3);
        readings.add(reading4);
        readings.add(reading5);
        user.setReadings(readings);
        userRepository.save(user);

        // Exercise
        User updatedRead = userService.markAsRead(user.getId(), book.getId());

        // Verify
        assertEquals(9, updatedUser.getPoints());

        Set<String> trophies = updatedRead.getTrophies();
        assertEquals(1, trophies.size());
        */
        
        //assertEquals("Leitor de " + book.getGenre(), trophy.getName());
    }
    
    @Test
	public void testUpdateRankingWith11Users() {
	    Ranking ranking = new Ranking();
	    User user1 = new User("Alice", "alice@gmail.com", "1234566");
	    user1.setPoints(10);
	    ranking.addUser(user1);

	    User user2 = new User("Bob", "bob@gmail.com", "154878");
	    user2.setPoints(20);
	    ranking.addUser(user2);

	    User user3 = new User("Charlie", "charlie@gmail.com", "548484");
	    user3.setPoints(30);
	    ranking.addUser(user3);

	    User user4 = new User("Dave", "dave@gmail.com", "5648746");
	    user4.setPoints(40);
	    ranking.addUser(user4);

	    User user5 = new User("Eve", "eve@gmail.com", "5544841");
	    user5.setPoints(50);
	    ranking.addUser(user5);

	    User user6 = new User("Frank", "frank@gmail.com", "6549894");
	    user6.setPoints(60);
	    ranking.addUser(user6);

	    User user7 = new User("Grace", "grace@gmail.com", "54878");
	    user7.setPoints(70);
	    ranking.addUser(user7);

	    User user8 = new User("Harry", "harry@gmail.com", "6966488");
	    user8.setPoints(80);
	    ranking.addUser(user8);

	    User user9 = new User("Isabel", "isabel@gmail.com", "6458977");
	    user9.setPoints(90);
	    ranking.addUser(user9);

	    User user10 = new User("Jack", "jack@gmail.com", "56857897");
	    user10.setPoints(100);
	    ranking.addUser(user10);

	    User user11 = new User("Kelly", "kelly@gmail.com", "2659889");
	    user11.setPoints(110);
	    ranking.addUser(user11);

	    List<User> topUsers = ranking.getTopUsers();
	    assertEquals(10, topUsers.size());

	    // check the position of a specific user
	    List<User> users = ranking.getUsers();
	    assertEquals(10, users.indexOf(user1));

	    // check the order of users in the ranking
	    assertEquals(user11, topUsers.get(0));
	    assertEquals(user10, topUsers.get(1));
	    assertEquals(user9, topUsers.get(2));
	    assertEquals(user8, topUsers.get(3));
	    assertEquals(user7, topUsers.get(4));
	    assertEquals(user6, topUsers.get(5));
	    assertEquals(user5, topUsers.get(6));
	    assertEquals(user4, topUsers.get(7));
	    assertEquals(user3, topUsers.get(8));
	    assertEquals(user2, topUsers.get(9));
	}

}