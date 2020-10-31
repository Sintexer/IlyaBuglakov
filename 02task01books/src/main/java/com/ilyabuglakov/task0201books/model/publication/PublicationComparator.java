package com.ilyabuglakov.task0201books.model.publication;

import java.util.Comparator;

public class PublicationComparator implements Comparator<Publication> {
    @Override
    public int compare(Publication o1, Publication o2) {
        return Comparator.comparing(Publication::getName)
                .thenComparing(Publication::getYearOfPublishing)
                .thenComparing(Publication::getPublishingHouse)
                .thenComparing(Publication::getNumberOfPages)
                .compare(o1, o2);
    }
}
