package com.capgemini;

import java.time.LocalDate;

public class Trophy {
    private String name;
    private String image;
    private LocalDate obtainedDate;
    
	public Trophy() {
		super();
	}

    public Trophy(String name, String image, LocalDate obtainedDate) {
        this.name = name;
        this.image = image;
        this.obtainedDate = obtainedDate;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }


	public void setName(String name) {
		this.name = name;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setObtainedDate(LocalDate obtainedDate) {
		this.obtainedDate = obtainedDate;
	}

	public LocalDate getObtainedDate() {
        return obtainedDate;
    }

    public String addGenreTrophy(GenrEnum genre) {
        String trophyName = "Leitor de " + genre;
        return this.name = trophyName;
    }
}