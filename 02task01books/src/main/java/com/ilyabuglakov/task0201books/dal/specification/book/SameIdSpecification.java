package com.ilyabuglakov.task0201books.dal.specification.book;

import com.ilyabuglakov.task0201books.model.book.Book;

/**
 * Returns true if given Book has adjusted id
 */
public class SameIdSpecification implements BookSpecification {
    private long id;

    public void setName(long id) {
        this.id = id;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return id ==criteria.getId();
    }
}
