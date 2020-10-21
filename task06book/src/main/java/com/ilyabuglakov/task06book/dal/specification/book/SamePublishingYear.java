package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.model.book.Book;

public class SamePublishingYear extends BookSpecification {
    public SamePublishingYear(Book book) {
        super(book);
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return book.getYearOfPublishing().equals(criteria.getYearOfPublishing());
    }
}
