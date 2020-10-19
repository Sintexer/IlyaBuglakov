package com.ilyabuglakov.task06book.model.book;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class Book {

    private final String name;
    private final int numberOfPages;
    private final Set<String> authors;
    private final String publishingHouse;
    private final Year yearOfPublishing;

    public Book(String name, int numberOfPages, Set<String> authors, String publishingHouse, Year yearOfPublishing) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.authors = authors;
        this.publishingHouse = publishingHouse;
        this.yearOfPublishing = yearOfPublishing;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public Set<String> getAuthors() {
        return new HashSet<>(authors);
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public Year getYearOfPublishing() {
        return yearOfPublishing;
    }

}
