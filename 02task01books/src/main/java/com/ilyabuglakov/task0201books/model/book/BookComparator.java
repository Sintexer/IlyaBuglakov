package com.ilyabuglakov.task0201books.model.book;

import com.ilyabuglakov.task0201books.model.publication.PublicationComparator;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    public static Comparator<Book> comparingAuthors() {
        return Comparator.comparing(book -> book.getAuthors().stream()
                .sorted()
                .findFirst().orElse(""));
    }

    @Override
    public int compare(Book o1, Book o2) {
        return comparingAuthors()
                .thenComparing(PublicationComparator.getComparator())
                .compare(o1, o2);
    }

}
