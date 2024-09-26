package com.e2.wfm.gurobidemo.dcm.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.builder.CompareToBuilder;

public class BookComparator implements Comparator<Book> {
    List<String> sortFields = new ArrayList<String>();
    
    public BookComparator(List<String> sortFields) {
        this.sortFields.addAll(sortFields);
    }
    
    @Override
    public int compare(Book book1, Book book2) {
        if (this.sortFields.isEmpty()) {
            return 0;
        }
        
        CompareToBuilder builder = new CompareToBuilder();
        for (String sortField : sortFields) {
            if (sortField.startsWith("-")) {
                String fieldName = sortField.substring(1);
                Function<Book, ?> field = Book.SORT_MAP.get(fieldName);
                if (field == null) {
                    throw new IllegalArgumentException("Invalid sort field: " + fieldName);
                }
                Object parameter1 = field.apply(book1);
                Object parameter2 = field.apply(book2);
                builder.append(parameter1, parameter2);
            } else {
                String fieldName = sortField;
                Function<Book, ?> field = Book.SORT_MAP.get(fieldName);
                if (field == null) {
                    throw new IllegalArgumentException("Invalid sort field: " + fieldName);
                }
                Object parameter1 = field.apply(book1);
                Object parameter2 = field.apply(book2);
                builder.append(parameter1, parameter2);
            }
        }
        return builder.toComparison();        
    }
}
