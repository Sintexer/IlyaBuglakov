package com.ilyabuglakov.task06book.storage;

import com.ilyabuglakov.task06book.model.book.Book;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BookSet {
    private Set<Book> books;

    public BookSet(Collection<Book> books) {
        this.books = new HashSet<>(books);
    }

    public BookSet() {
        books = new HashSet<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book){
        return books.remove(book);
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<Book> getBooks() {
        return new HashSet<>(books);
    }

}
