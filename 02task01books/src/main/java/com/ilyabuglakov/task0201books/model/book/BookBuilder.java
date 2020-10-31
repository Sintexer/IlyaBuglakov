package com.ilyabuglakov.task0201books.model.book;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

/**
 * BookBuilder is implementation of the Builder pattern, specified for Book class.
 * Used for convenient creation of Book object.
 */
public class BookBuilder {

    private String name = "None";
    private int numberOfPages = 0;
    private Set<String> authors = new HashSet<>();
    private String publishingHouse = "None";
    private Year yearOfPublishing = Year.of(0);

    /**
     * @return Book from given parameters.
     */
    public Book build() {
        return Book.of(name, numberOfPages, publishingHouse, yearOfPublishing, authors);
    }

    public BookBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public BookBuilder setAuthors(Set<String> authors) {
        this.authors = authors;
        return this;
    }

    public BookBuilder setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
        return this;
    }

    public BookBuilder setYearOfPublishing(Year yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
        return this;
    }
}
