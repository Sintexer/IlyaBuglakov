package com.ilyabuglakov.task06book.dal.specification.book;

import com.ilyabuglakov.task06book.dal.specification.Specification;
import com.ilyabuglakov.task06book.model.book.Book;

import java.time.Year;

public class SamePublishingYear implements Specification<Book> {
    private Year yearOfPublishing;

    public void setYearOfPublishing(Year yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return yearOfPublishing.equals(criteria.getYearOfPublishing());
    }
}
