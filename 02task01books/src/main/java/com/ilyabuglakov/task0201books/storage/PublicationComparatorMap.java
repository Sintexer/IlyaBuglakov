package com.ilyabuglakov.task0201books.storage;

import com.ilyabuglakov.task0201books.bean.SpecificationName;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import com.ilyabuglakov.task0201books.model.publication.PublicationComparator;

import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;

public class PublicationComparatorMap {
    private static final PublicationComparatorMap INSTANCE = new PublicationComparatorMap();
    private EnumMap<SpecificationName, Comparator<Publication>> comparators =
            new EnumMap<>(SpecificationName.class);


    private PublicationComparatorMap() {
        add(SpecificationName.BY_ID, PublicationComparator.comparingId());
        add(SpecificationName.BY_NAME, PublicationComparator.comparingName());
        add(SpecificationName.BY_NUMBER_OF_PAGES, PublicationComparator.comparingNumberOfPages());
        add(SpecificationName.BY_PUBLISHING_HOUSE, PublicationComparator.comparingPublishingHouse());
        add(SpecificationName.BY_YEAR_OF_PUBLISHING, PublicationComparator.comparingYearOfPublishing());

    }

    public static PublicationComparatorMap getInstance() {
        return INSTANCE;
    }

    private void add(SpecificationName specificationName, Comparator<Publication> comparator) {
        comparators.put(specificationName, comparator);
    }

    public Collection<SpecificationName> getSpecificationsNames() {
        return comparators.keySet();
    }

    public Comparator<Publication> get(SpecificationName name) {
        return comparators.get(name);
    }
}
