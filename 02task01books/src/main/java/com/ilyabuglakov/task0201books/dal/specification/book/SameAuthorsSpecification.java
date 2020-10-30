package com.ilyabuglakov.task0201books.dal.specification.book;

import com.ilyabuglakov.task0201books.dal.specification.Specification;
import com.ilyabuglakov.task0201books.model.book.Book;

import java.util.HashSet;
import java.util.Set;

public class SameAuthorsSpecification implements Specification<Book> {
    private Set<String> authors;

    public void setAuthors(Set<String> authors) {
        this.authors = new HashSet<>(authors);
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return criteria.getAuthors().containsAll(authors);
    }
}
