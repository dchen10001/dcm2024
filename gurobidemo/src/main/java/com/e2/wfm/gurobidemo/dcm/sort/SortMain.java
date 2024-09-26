package com.e2.wfm.gurobidemo.dcm.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.builder.CompareToBuilder;

public class SortMain {
    private static final Map<String, Function<Book, ?>> SORT_MAP = new HashMap<>();
    static {
        SORT_MAP.put("title", Book::getTitle);
        SORT_MAP.put("author", Book::getAuthor);
        SORT_MAP.put("publishedYear", Book::getPublishedYear);
        SORT_MAP.put("price", Book::getPrice);
        SORT_MAP.put("genre", Book::getGenre);
    }
    
    public static void main(String[] args) {
        List<Book> booklist = new ArrayList<Book>();
        Book book1 = new Book("Java", "James Gosling", "1995", 100.0, "Programming");
        Book book2 = new Book("C++", "Bjarne Stroustrup", "1985", 90.0, "Programming");
        Book book3 = new Book("Python", "Guido van Rossum", "1991", 80.0, "Programming");
        Book book4 = new Book("The Da Vinci Code", "Dan Brown", "2003", 70.0, "Fiction");
        Book book5 = new Book("The Alchemist", "Paulo Coelho", "1988", 60.0, "Fiction");
        Book book6 = new Book("The Catcher in the Rye", "J.D. Salinger", "1951", 50.0, "Fiction");
        
        booklist.add(book1);
        booklist.add(book2);
        booklist.add(book3);
        booklist.add(book4);
        booklist.add(book5);
        booklist.add(book6);
        
        List<String> sortFields = new ArrayList<String>();
        sortFields.add("-title");
        sortFields.add("-author");
        sortFields.add("-publishedYear");
        sortFields.add("-price");
        sortFields.add("-genre");
        
        // TODO Auto-generated method stub
        CompareToBuilder builder = null;
    }

    private static List<Book> sortBooks(List<Book> booklist, List<String> sortFields) {
        if (sortFields.isEmpty() || booklist.isEmpty()) {
            return booklist;
        }
        
        booklist.sort((book1, book2) -> {
            CompareToBuilder builder = new CompareToBuilder();
            for (String sortField : sortFields) {
                if (sortField.startsWith("-")) {
                    String fieldName = sortField.substring(1);
                    Function<Book, ?> field = SORT_MAP.get(fieldName);
                    if (field == null) {
                        throw new IllegalArgumentException("Invalid sort field: " + fieldName);
                    }
                    Object parameter1 = field.apply(book1);
                    Object parameter2 = field.apply(book2);
                    builder.append(parameter1, parameter2);
                } else {
                    String fieldName = sortField;
                    Function<Book, ?> field = SORT_MAP.get(fieldName);
                    if (field == null) {
                        throw new IllegalArgumentException("Invalid sort field: " + fieldName);
                    }
                    Object parameter1 = field.apply(book1);
                    Object parameter2 = field.apply(book2);
                    builder.append(parameter1, parameter2);
                }

            }
            return builder.toComparison();
        });
        return booklist;
    }
}
