package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.dal.specification.Specification;
import com.ilyabuglakov.task06book.model.book.Book;

public class SameBookSpecification implements Specification<Book> {
    private Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return book.equals(criteria);
    }

}
