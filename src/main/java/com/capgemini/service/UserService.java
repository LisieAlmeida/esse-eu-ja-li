package com.capgemini.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.capgemini.Book;
import com.capgemini.GenrEnum;
import com.capgemini.Reading;
import com.capgemini.Trophy;
import com.capgemini.User;
import com.capgemini.repositories.BookRepository;
import com.capgemini.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    /*public void addPoints(Long userId, Integer points) {
        User user = getUserById(userId);
        user.setPoints(user.getPoints() + points);
        userRepository.save(user);
    }*/
    
    /*@GetMapping("/ranking")
    public List<UserRankingDTO> getRanking() {
        return userService.getRanking();
    }*/

    /*public void addTrophy(Long userId, Trophy trophy) {
        User user = findById(userId);
        user.getTrophies().add(trophy);
        userRepository.save(user);
    }*/
    /*public void addPoints(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        
        // Adiciona 1 ponto ao usuário
        //user.setPoints(user.getPoints() + 1);
        
        // Calcula a pontuação adicional baseada no número de páginas do livro lido
        int pageCount = book.getPageCount();
        int basePoints = 1;
        int additionalPoints = (pageCount / 100) + (pageCount % 100 > 0 ? 1 : 0);
        int totalPoints = basePoints + additionalPoints;
        
        user.setPoints(totalPoints);
        userRepository.save(user);
        
        // Adiciona o troféu correspondente se o usuário leu 5 livros do mesmo estilo
       /*String style = book.getStyle();
        int styleCount = (int) user.getBooks().stream()
                .filter(b -> b.getStyle().equals(style))
                .count();
        if (styleCount == 5) {
            String trophyName = "Leitor de " + style;
            user.addTrophy(trophyName);
        }
        
        userRepository.save(user);
    }*/
    public User addPoints(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        int pageCount = book.getPageCount();
        int basePoints = 1;
        int additionalPoints = (pageCount / 100) + (pageCount % 100 > 0 ? 1 : 0);
        int totalPoints = basePoints + additionalPoints;
        
        user.setPoints(totalPoints);

       ;
        userRepository.save(user);

        // Adiciona o livro à lista de livros lidos pelo usuário
        user.getBooks().add(book);
        userRepository.save(user);

        // Adiciona troféu do gênero do livro lido, caso necessário
        Trophy trophy = new Trophy();
        GenrEnum bookGenre = book.getGenre();
        String trophyName = trophy.addGenreTrophy(bookGenre);
        
        
        if (!user.hasTrophy(trophyName)) {
            user.addTrophy(trophyName);
        }

        // Atualiza leitura do usuário
        markAsRead(user.getId(), book.getId());

        user = userRepository.save(user);

        // Retorna o usuário atualizado
        return user;
    }
    
    public User updateUser(Long userId, User updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        // set new values to user object
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

        // ... set other attributes
        
        return userRepository.save(user);
    }
    
    public User markAsRead(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        

        // Verificar se o livro já está marcado como lido
        if (user.getReadings().stream().anyMatch(r -> r.getBook().equals(book))) {
            throw new InvalidRequestException("Book already marked as read by user");
        } 
        
       user.getBooks().add(book);

        // Adicionar o livro aos Reading do usuário
        Reading reading = new Reading(user, book);
        user.getReadings().add(reading);

        // Adicionar a pontuação do livro ao usuário
        int pageCount = book.getPageCount();
        int basePoints = 1;
        int additionalPoints = (pageCount / 100) + (pageCount % 100 > 0 ? 1 : 0);
        int totalPoints = basePoints + additionalPoints;
       
        user.setPoints(totalPoints);

        // Verificar se o usuário conquistou algum troféu
        Trophy trophy = new Trophy();
        GenrEnum bookGenre = book.getGenre();
        String trophyName = trophy.addGenreTrophy(bookGenre);
        
        /*if (!user.hasTrophy(trophyName)) {
        	int styleCount = (int) user.getBooks().stream()
                    .filter(b -> b.getGenre().equals(trophyName))
                    .count();
        	if (styleCount == 5) {
            user.addTrophy(trophyName);
        	}
        }*/
        List<Book> booksWithGenre = user.getBooks().stream()
                .filter(bookGen -> book.getGenre() == bookGenre)
                .collect(Collectors.toList());
        if (!user.hasTrophy(trophyName)) {
        	
            if (booksWithGenre.size() >= 5) {
                user.addTrophy(trophyName);
            }
        }
        
        
        /*GenrEnum style = book.getGenre();
        int styleCount = (int) user.getBooks().stream()
                .filter(b -> b.getGenre().equals(style))
                .count();
        if (styleCount == 5) {
            String trophyName = "Leitor de " + style;
            user.addTrophy(trophyName);
        }*/
        
       /* for (String genre : user.getGenres()) {
            if (user.getReadings().stream().filter(r -> r.isRead() && r.getBook().getGenre().equals(genre)).count() % 5 == 0) {
                user.addTrophy("Leitor de " + genre);
            }
        }*/

        // Salvar o usuário atualizado no banco de dados
        user = userRepository.save(user);

        return user;
    }

   


}
