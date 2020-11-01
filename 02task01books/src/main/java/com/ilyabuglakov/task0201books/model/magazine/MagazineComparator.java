package com.ilyabuglakov.task0201books.model.magazine;

import com.ilyabuglakov.task0201books.model.publication.PublicationComparator;

import java.util.Comparator;

public class MagazineComparator implements Comparator<Magazine> {

    public static Comparator<Magazine> comparingMagazineType() {
        return Comparator.comparing(Magazine::getType);
    }

    @Override
    public int compare(Magazine o1, Magazine o2) {
        return comparingMagazineType()
                .thenComparing(PublicationComparator.getComparator())
                .compare(o1, o2);
    }
}
