package com.ilyabuglakov.task0201books.model.publication;

import java.util.Comparator;

public class PublicationComparator implements Comparator<Publication> {

    public static Comparator<Publication> comparingId() {
        return Comparator.comparing(Publication::getId);
    }

    public static Comparator<Publication> comparingName() {
        return Comparator.comparing(Publication::getName);
    }

    public static Comparator<Publication> comparingYearOfPublishing() {
        return Comparator.comparing(Publication::getYearOfPublishing);
    }

    public static Comparator<Publication> comparingNumberOfPages() {
        return Comparator.comparing(Publication::getNumberOfPages);
    }

    public static Comparator<Publication> comparingPublishingHouse() {
        return Comparator.comparing(Publication::getPublishingHouse);
    }

    public static Comparator<Publication> getComparator() {
        return new PublicationComparator();
    }

    @Override
    public int compare(Publication o1, Publication o2) {
        return Comparator.comparing(Publication::getName)
                .thenComparing(Publication::getYearOfPublishing)
                .thenComparing(Publication::getPublishingHouse)
                .thenComparing(Publication::getNumberOfPages)
                .compare(o1, o2);
    }
}
