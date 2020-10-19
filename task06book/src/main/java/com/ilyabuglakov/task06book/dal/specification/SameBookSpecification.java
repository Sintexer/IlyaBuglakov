package com.ilyabuglakov.task06book.dal.specification;

import com.ilyabuglakov.task06book.model.book.Book;

public class SameBookSpecification implements Specification<Book> {

    private Book book;

    public SameBookSpecification(Book book){
        this.book = book;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return book.equals(criteria);
    }

}
