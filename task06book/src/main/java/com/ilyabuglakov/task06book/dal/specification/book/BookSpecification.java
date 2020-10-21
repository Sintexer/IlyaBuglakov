package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.dal.specification.Specification;
import com.ilyabuglakov.task06book.model.book.Book;

public abstract class BookSpecification implements Specification<Book> {
    protected Book book;

    public BookSpecification(Book book){
        this.book = book;
    }

}
