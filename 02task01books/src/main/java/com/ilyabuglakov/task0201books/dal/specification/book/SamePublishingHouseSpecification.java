package com.ilyabuglakov.task0201books.dal.specification.book;

import com.ilyabuglakov.task0201books.dal.specification.Specification;
import com.ilyabuglakov.task0201books.model.book.Book;

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
