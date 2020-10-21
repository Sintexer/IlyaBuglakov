package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.model.book.Book;

public class SameBookSpecification extends BookSpecification {


    public SameBookSpecification(Book book) {
        super(book);
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return book.equals(criteria);
    }

}
