package com.ilyabuglakov.task0201books.model.book;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    public static Comparator<Book> comparingName() {
        return Comparator.comparing(Book::getName);
    }

    public static Comparator<Book> comparingYearOfPublishing() {
        return Comparator.comparing(Book::getYearOfPublishing);
    }

    public static Comparator<Book> comparingNumberOfPages() {
        return Comparator.comparing(Book::getNumberOfPages);
    }

    public static Comparator<Book> comparingPublishingHouse() {
        return Comparator.comparing(Book::getPublishingHouse);
    }

    public static Comparator<Book> comparingAuthors() {
        return Comparator.comparing(book -> book.getAuthors().stream()
                .sorted()
                .findFirst().orElse(""));
    }

    @Override
    public int compare(Book o1, Book o2) {
        return comparingName()
                .thenComparing(comparingYearOfPublishing())
                .thenComparing(comparingNumberOfPages())
                .thenComparing(comparingPublishingHouse())
                .thenComparing(comparingAuthors())
                .compare(o1, o2);
    }

}
