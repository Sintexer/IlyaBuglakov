package com.ilyabuglakov.task06book.model.book;

import java.time.Year;
import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numberOfPages == book.numberOfPages &&
                Objects.equals(name, book.name) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(publishingHouse, book.publishingHouse) &&
                Objects.equals(yearOfPublishing, book.yearOfPublishing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfPages, authors, publishingHouse, yearOfPublishing);
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
