package com.ilyabuglakov.task0201books.dal.specification.book;

import com.ilyabuglakov.task0201books.model.book.Book;

import java.time.Year;

public class SamePublishingYear implements BookSpecification {
    private Year yearOfPublishing;

    public void setYearOfPublishing(Year yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    @Override
    public boolean isSatisfiedBy(Book criteria) {
        return yearOfPublishing.equals(criteria.getYearOfPublishing());
    }
}
