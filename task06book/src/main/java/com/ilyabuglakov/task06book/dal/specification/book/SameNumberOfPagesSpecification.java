package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.dal.specification.Specification;
import com.ilyabuglakov.task06book.model.book.Book;

public class SameNumberOfPagesSpecification implements Specification<Book> {
    private int numberOfPages;

    public void setNumberOfPages(int numberOfPages) {
        numberOfPages = numberOfPages;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return numberOfPages == criteria.getNumberOfPages();
    }
}
