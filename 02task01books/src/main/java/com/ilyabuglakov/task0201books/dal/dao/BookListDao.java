package com.ilyabuglakov.task0201books.dal.dao;

import com.ilyabuglakov.task0201books.dal.specification.Specification;
import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.exception.DaoRemoveException;
import com.ilyabuglakov.task0201books.exception.DaoWrongTypeException;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import com.ilyabuglakov.task0201books.storage.BookList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookListDao implements GenericDao<Book> {
    private BookList bookList = new BookList();

    @Override
    public void set(int index, Publication publication) throws DaoWrongTypeException {
        if (publication.getClass() != Book.class)
            throw new DaoWrongTypeException("Error while trying to set something not book to bookList");
        set(index, (Book) publication);
    }

    public void set(int index, Book newInstance) {
        bookList.getBooks().set(index, newInstance);
    }

    @Override
    public void clear() {
        bookList.setBooks(new ArrayList<>());
    }

    @Override
    public void add(Publication publication) throws DaoAddException {
        if (publication.getClass() != Book.class)
            throw new DaoAddException("Error while trying to add something not book to bookList");
        Book book = (Book) publication;
        if (bookList.getBooks().contains(book))
            throw new DaoAddException("Book is already in BookList");
        bookList.addBook(book);
    }

    @Override
    public void remove(Publication publication) throws DaoRemoveException {
        if (publication.getClass() != Book.class)
            throw new DaoRemoveException("Error while trying to remove something not book from bookList");
        remove((Book) publication);
    }

    public void remove(Book book) throws DaoRemoveException {
        if (!bookList.removeBook(book))
            throw new DaoRemoveException("Cant remove book from BookList");
    }

    @Override
    public Book get(int index) {
        return bookList.getBooks().get(index);
    }

    @Override
    public int indexOf(Publication publication) throws DaoWrongTypeException {
        if (publication.getClass() != Book.class)
            throw new DaoWrongTypeException("Error while trying to find something not book in bookList");
        return indexOf((Book) publication);
    }

    public int indexOf(Book o) {
        return bookList.getBooks().indexOf(o);
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

    @Override
    public void sortBy(Comparator<? super Book> comparator) {
        bookList.setBooks(bookList.getBooks().stream()
                .sorted(comparator)
                .collect(Collectors.toList()));
    }
}
