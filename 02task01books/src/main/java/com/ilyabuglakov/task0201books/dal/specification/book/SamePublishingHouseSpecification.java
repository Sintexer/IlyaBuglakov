package com.ilyabuglakov.task0201books.dal.specification.book;

import com.ilyabuglakov.task0201books.model.book.Book;

/**
 * Returns true if given Book has adjusted publishingHouse
 */
public class SamePublishingHouseSpecification implements BookSpecification {
    private String publishingHouse;

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return publishingHouse.equals(criteria.getPublishingHouse());
    }
}
