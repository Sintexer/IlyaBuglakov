package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.dal.specification.Specification;
import com.ilyabuglakov.task06book.model.book.Book;

public class SameNameSpecification implements Specification<Book> {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return name.equals(criteria.getName());
    }
}
