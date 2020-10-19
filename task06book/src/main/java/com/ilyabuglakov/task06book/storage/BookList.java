package com.ilyabuglakov.task06book.storage;

import com.ilyabuglakov.task06book.model.book.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookList {
    private List<Book> books;

    public BookList(Collection<Book> books) {
        this.books = new ArrayList<>(books);
    }

    public BookList() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book){
        return books.remove(book);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

}
