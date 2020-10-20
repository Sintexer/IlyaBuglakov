package com.ilyabuglakov.task06book.dal.repository;

import com.ilyabuglakov.task06book.dal.dao.BookListDao;
import com.ilyabuglakov.task06book.dal.specification.Specification;
import com.ilyabuglakov.task06book.model.book.Book;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    private static BookRepository instance = new BookRepository();
    private BookListDao dao;
    
    private BookRepository(){
        dao = new BookListDao();
    }

    public static BookRepository getInstance() {
        return instance;
    }

    public void add(Book book){
        dao.add(book);
    }

    public List<Book> getBooks(){
        return dao.getAll();
    }

    public Optional<Book> getBookByCriteria(Specification<Book> criteria){
        return dao.findByCriteria(criteria);
    }

    public List<Book> getBooksByCriteria(Specification<Book> criteria){
        return dao.findAllByCriteria(criteria);
    }

    public void sortBy(Comparator<Book> comparator){
        dao.sortBy(comparator);
    }
}
