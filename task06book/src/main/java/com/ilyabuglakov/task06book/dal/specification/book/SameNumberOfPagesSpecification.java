package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.model.book.Book;

public class SameNumberOfPagesSpecification extends BookSpecification {
    public SameNumberOfPagesSpecification(Book book) {
        super(book);
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return book.getNumberOfPages() == criteria.getNumberOfPages();
    }
}
