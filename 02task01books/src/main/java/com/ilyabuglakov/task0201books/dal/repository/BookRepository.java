package com.ilyabuglakov.task0201books.dal.repository;

import com.ilyabuglakov.task0201books.dal.dao.BookListDao;
import com.ilyabuglakov.task0201books.dal.specification.Specification;
import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.exception.DaoRemoveException;
import com.ilyabuglakov.task0201books.model.book.Book;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    private static BookRepository instance = new BookRepository();
    private BookListDao dao;

    private BookRepository() {
        dao = new BookListDao();
    }

    public static BookRepository getInstance() {
        return instance;
    }

    public void add(Book book) throws DaoAddException {
        dao.add(book);
    }

    public void remove(Book book) throws DaoRemoveException {
        dao.remove(book);
    }

    public List<Book> getBooks() {
        return dao.getAll();
    }

    public void setBooks(List<Book> books) {
        dao.setAll(books);
    }

    public void clear() {
        dao.clear();
    }

    public Optional<Book> getBookByCriteria(Specification<Book> criteria) {
        return dao.findByCriteria(criteria);
    }

    public List<Book> getBooksByCriteria(Specification<Book> criteria) {
        return dao.findAllByCriteria(criteria);
    }

    public void sortBy(Comparator<Book> comparator) {
        dao.sortBy(comparator);
    }
}
