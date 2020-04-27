package com.tutorial.models;

public class Movie {
    public Movie(String name, int likes, Genre genre) {
        this.name = name;
        this.likes = likes;
        this.genre = genre;
    }

    public Movie(String name, int likes) {
        this.name = name;
        this.likes = likes;
    }

    public Movie setName(String name) {
        this.name = name;
        return this;
    }

    private String name;
    private int likes;

    private Genre genre = Genre.ACTION;

    public int getLikes() {
        return likes;
    }

    public String getName() {
        return name;
    }
    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return name;
    }
}
