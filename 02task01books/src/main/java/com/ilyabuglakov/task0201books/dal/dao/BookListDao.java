package com.ilyabuglakov.task0201books.dal.dao;

import com.ilyabuglakov.task0201books.dal.specification.Specification;
import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.exception.DaoRemoveException;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.storage.BookList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookListDao implements GenericDao<Book> {
    private BookList bookList = new BookList();

    @Override
    public void clear() {
        bookList.setBooks(new ArrayList<>());
    }

    @Override
    public void add(Book book) throws DaoAddException {
        if (bookList.getBooks().contains(book))
            throw new DaoAddException("Book is already in BookList");
        bookList.addBook(book);
    }

    @Override
    public void remove(Book book) throws DaoRemoveException {
        if (!bookList.removeBook(book))
            throw new DaoRemoveException("Cant remove book from BookSet");
    }

    @Override
    public List<Book> getAll() {
        return bookList.getBooks();
    }

    @Override
    public void setAll(List<Book> content) {
        bookList.setBooks(new ArrayList<>(content));
    }

    @Override
    public <S extends Specification<Book>> Optional<Book> findByCriteria(S criteria) {
        return bookList.getBooks().stream()
                .filter(criteria::isSatisfiedBy)
                .findFirst();
    }

    @Override
    public <S extends Specification<Book>> List<Book> findAllByCriteria(S criteria) {
        return bookList.getBooks().stream()
                .filter(criteria::isSatisfiedBy)
                .collect(Collectors.toList());
    }

    public void sortBy(Comparator<Book> comparator) {
        bookList.setBooks(bookList.getBooks().stream()
                .sorted(comparator)
                .collect(Collectors.toList()));
    }
}
