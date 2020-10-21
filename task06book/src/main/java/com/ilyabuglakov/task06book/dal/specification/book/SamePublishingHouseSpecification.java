package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.model.book.Book;

public class SamePublishingHouseSpecification extends BookSpecification {
    public SamePublishingHouseSpecification(Book book) {
        super(book);
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return book.getPublishingHouse().equals(criteria.getPublishingHouse());
    }
}
