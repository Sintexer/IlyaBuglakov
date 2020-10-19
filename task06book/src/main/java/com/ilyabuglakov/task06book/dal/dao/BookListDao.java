package com.ilyabuglakov.task06book.dal.dao;

import com.ilyabuglakov.task06book.dal.specification.Specification;
import com.ilyabuglakov.task06book.exception.DaoRemoveException;
import com.ilyabuglakov.task06book.model.book.Book;
import com.ilyabuglakov.task06book.storage.BookList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookListDao implements GenericDao<Book> {
    private BookList bookList;

    @Override
    public void add(Book book) {
        bookList.addBook(book);
    }

    @Override
    public void remove(Book book) throws DaoRemoveException {
        if(!bookList.removeBook(book))
            throw new DaoRemoveException("Cant remove book from BookSet");
    }

    @Override
    public List<Book> getAll() {
        return bookList.getBooks();
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

    public void sortBy(Comparator<Book> comparator){
        bookList.setBooks(bookList.getBooks().stream()
        .sorted(comparator)
        .collect(Collectors.toList()));
    }
}
