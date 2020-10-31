package com.ilyabuglakov.task0201books.dal.specification.book;

import com.ilyabuglakov.task0201books.model.book.Book;

import java.util.HashSet;
import java.util.Set;

/**
 * Returns true if given Book has all adjusted authors
 */
public class SameAuthorsSpecification implements BookSpecification {
    private Set<String> authors;

    public void setAuthors(Set<String> authors) {
        this.authors = new HashSet<>(authors);
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return criteria.getAuthors().containsAll(authors);
    }
}
