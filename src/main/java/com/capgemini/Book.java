package com.capgemini;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer pageCount;

    @Column(nullable = false)
    private GenrEnum genre;
    
    @OneToMany(mappedBy = "book")
    private List<Reading> readings;

    
    

	public Book() {
    }

    public Book(String title, String author, Integer pageCount, GenrEnum genre) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.genre = genre;
    }
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	

	public GenrEnum getGenre() {
		return genre;
	}

	public void setGenre(GenrEnum fantasy) {
		this.genre = fantasy;
	}
	
	public List<Reading> getReadings() {
		return readings;
	}

	public void setReadings(List<Reading> readings) {
		this.readings = readings;
	}
	

	public Integer getPointValue() {
        return (int) Math.ceil(pageCount / 100.0);
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(author, genre, id, pageCount, readings, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && genre == other.genre && Objects.equals(id, other.id)
				&& Objects.equals(pageCount, other.pageCount) && Objects.equals(readings, other.readings)
				&& Objects.equals(title, other.title);
	}
}
