package com.ilyabuglakov.task0201books.dal.specification.magazine;

import com.ilyabuglakov.task0201books.model.magazine.Magazine;

/**
 * Returns true if given Magazine has adjusted name
 */
public class SameNameSpecification implements MagazineSpecification {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(Magazine criteria) {
        return name.equals(criteria.getName());
    }
}