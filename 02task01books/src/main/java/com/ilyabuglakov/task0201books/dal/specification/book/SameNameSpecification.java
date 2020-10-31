package com.ilyabuglakov.task0201books.dal.specification.book;

import com.ilyabuglakov.task0201books.model.book.Book;

/**
 * Returns true if given Book has adjusted name
 */
public class SameNameSpecification implements BookSpecification {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return name.equals(criteria.getName());
    }
}
