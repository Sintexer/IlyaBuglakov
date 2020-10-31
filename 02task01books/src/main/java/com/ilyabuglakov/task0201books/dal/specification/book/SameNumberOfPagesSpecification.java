package com.ilyabuglakov.task0201books.dal.specification.book;

import com.ilyabuglakov.task0201books.model.book.Book;

/**
 * Returns true if given Book has adjusted numberOfPages
 */
public class SameNumberOfPagesSpecification implements BookSpecification {
    private int numberOfPages;

    public void setNumberOfPages(int numberOfPages) {
        numberOfPages = numberOfPages;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return numberOfPages == criteria.getNumberOfPages();
    }
}
