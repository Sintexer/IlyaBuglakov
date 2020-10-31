package com.ilyabuglakov.task0201books.dal.specification.book;

import com.ilyabuglakov.task0201books.model.book.Book;

/**
 * Returns true if each given Book field is equal to each adjusted book field.
 */
public class SameBookSpecification implements BookSpecification {
    private Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return book.equals(criteria);
    }

}
