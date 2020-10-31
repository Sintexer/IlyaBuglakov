package com.ilyabuglakov.task0201books.model.magazine;

import java.util.Comparator;

public class MagazineComparator implements Comparator<Magazine> {
    @Override
    public int compare(Magazine o1, Magazine o2) {
        return Comparator.comparing(Magazine::getType)
                .thenComparing(Magazine::getName)
                .thenComparing(Magazine::getYearOfPublishing)
                .thenComparing(Magazine::getPublishingHouse)
                .thenComparing(Magazine::getNumberOfPages)
                .compare(o1, o2);
    }
}
