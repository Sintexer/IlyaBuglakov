package com.ilyabuglakov.task0201books.model.book;

import com.ilyabuglakov.task0201books.model.publication.Publication;

import java.time.Year;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Book extends Publication {

    private final Set<String> authors;

    private Book(String name, int numberOfPages, String publishingHouse, Year yearOfPublishing, Set<String> authors) {
        super(name, numberOfPages, publishingHouse, yearOfPublishing);
        this.authors = authors;
    }

    public static Book of(String name, int numberOfPages, String publishingHouse,
                          Year yearOfPublishing, Set<String> author){
        return  new Book(name, numberOfPages, publishingHouse, yearOfPublishing, author);
    }

    @Override
    public String toString() {
        return new StringBuilder(getPrefix())
                .append("{").append(name)
                .append("|").append(numberOfPages)
                .append("|").append(publishingHouse)
                .append("|").append(yearOfPublishing)
                .append("|").append(String.join(",", authors))
                .append("}")
                .toString();
    }

    public static String getPrefix() {
        return "Book";
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

    public Set<String> getAuthors() {
        return new HashSet<>(authors);
    }

}
