package util;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.Book;
import com.capgemini.GenrEnum;
import com.capgemini.User;
import com.capgemini.controllers.BookController;
import com.capgemini.repositories.BookRepository;
import com.capgemini.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class LoadData  {
	
	
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookController bookController;

	
	@PostConstruct
	private void LoadUsers()  {
		 Book book1 = new Book();
	        book1.setTitle("Harry Potter and the Philosopher's Stone");
	        book1.setAuthor("J.K. Rowling");
	        book1.setGenre(GenrEnum.FANTASIA);
	        book1.setPageCount(124);

	        Book book2 = new Book();
	        book2.setTitle("To Kill a Mockingbird");
	        book2.setAuthor("Harper Lee");
	        book2.setGenre(GenrEnum.ROMANCE);
	        book2.setPageCount(345);
	        
	        bookController.createBook(book1);
	        bookController.createBook(book2);
	}
	
	
	

}
