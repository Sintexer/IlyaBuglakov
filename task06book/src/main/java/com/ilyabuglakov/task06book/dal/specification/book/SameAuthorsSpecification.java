package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.model.book.Book;

public class SameAuthorsSpecification extends BookSpecification {
    public SameAuthorsSpecification(Book book) {
        super(book);
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return criteria.getAuthors().containsAll(book.getAuthors());
    }
}
