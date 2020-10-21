package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.model.book.Book;

public class SameNameSpecification extends BookSpecification {

    public SameNameSpecification(Book book) {
        super(book);
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return book.getName().equals(criteria.getName());
    }
}
