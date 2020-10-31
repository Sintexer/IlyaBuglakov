package com.ilyabuglakov.task0201books.dal.specification.magazine;

import com.ilyabuglakov.task0201books.model.magazine.Magazine;

/**
 * Returns true if each given Book field is equal to each adjusted book field.
 */
public class SameMagazineSpecification implements MagazineSpecification {

    private Magazine magazine;

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    @Override
    public boolean isSatisfiedBy(Magazine criteria) {
        return false;
    }
}
