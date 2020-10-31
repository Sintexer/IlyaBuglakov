package com.ilyabuglakov.task0201books.dal.specification.book;

import com.ilyabuglakov.task0201books.model.book.Book;

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
