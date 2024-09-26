package com.e2.wfm.gurobidemo.dcm.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Book {
    public static final Map<String, Function<Book, ?>> SORT_MAP = new HashMap<>();
    static {
        SORT_MAP.put("title", Book::getTitle);
        SORT_MAP.put("author", Book::getAuthor);
        SORT_MAP.put("publishedYear", Book::getPublishedYear);
        SORT_MAP.put("price", Book::getPrice);
        SORT_MAP.put("genre", Book::getGenre);
    }
    
    private String title;
    private String author;
    private String publishedYear;
    private double price;
    private String genre;

    public Book(String title, String author, String publishedYear, double price, String genre) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.price = price;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }
}
