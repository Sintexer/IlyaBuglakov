package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.dal.specification.Specification;
import com.ilyabuglakov.task06book.model.book.Book;

public class SamePublishingHouseSpecification implements Specification<Book> {
    private  String publishingHouse;

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return publishingHouse.equals(criteria.getPublishingHouse());
    }
}
