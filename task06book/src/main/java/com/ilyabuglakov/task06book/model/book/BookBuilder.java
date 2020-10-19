package com.ilyabuglakov.task06book.model.book;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class BookBuilder {

    private String name = "NaN";
    private int numberOfPages = 0;
    private Set<String> authors = new HashSet<>();
    private String publishingHouse = "NaN";
    private Year yearOfPublishing = Year.of(0);

    public Book build(){
        return new Book(name, numberOfPages, authors,publishingHouse, yearOfPublishing);
    }

    public BookBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public BookBuilder addAuthor(String author){
        authors.add(author);
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
