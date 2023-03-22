package com.capgemini;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_book",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "book_id"))
	private Set<Book> books = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Reading> readings = new HashSet<>();

	@ElementCollection
	private Set<String> trophies = new HashSet<>();

	@Column(nullable = false)
	private int points;
	
	@Column(nullable = false)
	private int rankingPosition;
	
	

	
	

	/*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Ranking> rankings = new HashSet<>();*/

	

	public User() {}

	public User(String name, String email, String password) {
	    this.name = name;
	    this.email = email;
	    this.password = password;
	}

	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	public Set<Book> getBooks() {
	    return books;
	}

	public void setBooks(Set<Book> books) {
	    this.books = books;
	}

	public Set<Reading> getReadings() {
	    return readings;
	}

	public void setReadings(Set<Reading> readings) {
	    this.readings = readings;
	}

	public Set<String> getTrophies() {
	    return trophies;
	}

	public void setTrophies(Set<String> trophies) {
	    this.trophies = trophies;
	}

	public int getPoints() {
	    return points;
	}

	public void setPoints(int points) {
	    this.points = points;
	}
	
	public int getRankingPosition() {
		return rankingPosition;
	}

	public void setRankingPosition(int rankingPosition) {
		this.rankingPosition = rankingPosition;
	}

	/*public Set<Ranking> getRankings() {
	    return rankings;
	}

	public void setRankings(Set<Ranking> rankings) {
	    this.rankings = rankings;
	}*/

	public void addBook(Book book) {
	    books.add(book);
	}

	public void removeBook(Book book) {
	    books.remove(book);
	}

	public void addReading(Reading reading) {
	    readings.add(reading);
	}

	public void removeReading(Reading reading) {
	    readings.remove(reading);
	}

	public void addTrophy(String trophy) {
	    trophies.add(trophy);
	}

	public void removeTrophy(String trophy) {
	    trophies.remove(trophy);
	}
	
	public boolean hasTrophy(String trophyName) {
	    return trophies.contains(trophyName);
	}

	public void addPoints(int points) {
	    this.points += points;
	}

	public void removePoints(int points) {
	    this.points -= points;
	}

	/*public void addRanking(Ranking ranking) {
	    rankings.add(ranking);
	}

	public void removeRanking(Ranking ranking) {
	    rankings.remove(ranking);
	}*/
	

    // Construtores, getters e setters
}