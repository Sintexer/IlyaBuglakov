package com.ilyabuglakov.task06book.dal.dao;

import com.ilyabuglakov.task06book.dal.specification.Specification;
import com.ilyabuglakov.task06book.exception.DaoRemoveException;
import com.ilyabuglakov.task06book.model.book.Book;
import com.ilyabuglakov.task06book.storage.BookSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookSetDao implements GenericDao<Book> {
    private BookSet bookSet;


    @Override
    public void add(Book book) {
        bookSet.addBook(book);
    }

    @Override
    public void remove(Book book) throws DaoRemoveException {
        if(!bookSet.removeBook(book))
            throw new DaoRemoveException("Cant remove book from BookSet");
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(bookSet.getBooks());
    }

    @Override
    public <S extends Specification<Book>> Optional<Book> findByCriteria(S criteria) {
        return bookSet.getBooks().stream()
                .filter(criteria::isSatisfiedBy)
                .findFirst();
    }

    @Override
    public <S extends Specification<Book>> List<Book> findAllByCriteria(S criteria) {
        return bookSet.getBooks().stream()
                .filter(criteria::isSatisfiedBy)
                .collect(Collectors.toList());
    }
}
