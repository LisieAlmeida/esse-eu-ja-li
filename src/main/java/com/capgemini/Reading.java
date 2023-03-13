package com.capgemini;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "readings")
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    
    @Column
    private int pagesRead;

    

	@Column(nullable = false)
    private LocalDate date;

    public Reading() {}

    public Reading(User user, Book book) {
        this.user = user;
        this.book = book;
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    public int getPagesRead() {
		return pagesRead;
	}

	public void setPagesRead(int pagesRead) {
		this.pagesRead = pagesRead;
	}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate localDate) {
        this.date = localDate;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(book, date, id, pagesRead, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reading other = (Reading) obj;
		return Objects.equals(book, other.book) && Objects.equals(date, other.date) && Objects.equals(id, other.id)
				&& pagesRead == other.pagesRead && Objects.equals(user, other.user);
	}
    
    
}