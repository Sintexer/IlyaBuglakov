package com.ilyabuglakov.task0201books.dal.specification.magazine;

import com.ilyabuglakov.task0201books.model.magazine.Magazine;

/**
 * Returns true if given Magazine has adjusted name
 */
public class SamePublishingHouseSpecification implements MagazineSpecification {
    private String publishingHouse;

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean isSatisfiedBy(Magazine criteria) {
        return publishingHouse.equals(criteria.getPublishingHouse());
    }
}