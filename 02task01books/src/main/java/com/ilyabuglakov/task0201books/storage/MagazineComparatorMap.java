package com.ilyabuglakov.task0201books.storage;

import com.ilyabuglakov.task0201books.bean.SpecificationName;
import com.ilyabuglakov.task0201books.model.magazine.Magazine;
import com.ilyabuglakov.task0201books.model.magazine.MagazineComparator;
import com.ilyabuglakov.task0201books.model.publication.PublicationComparator;

import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;

public class MagazineComparatorMap {
    private static final MagazineComparatorMap INSTANCE = new MagazineComparatorMap();
    private EnumMap<SpecificationName, Comparator<? super Magazine>> comparators =
            new EnumMap<>(SpecificationName.class);


    private MagazineComparatorMap() {
        add(SpecificationName.BY_ID, PublicationComparator.comparingId());
        add(SpecificationName.BY_NAME, PublicationComparator.comparingName());
        add(SpecificationName.BY_NUMBER_OF_PAGES, PublicationComparator.comparingNumberOfPages());
        add(SpecificationName.BY_PUBLISHING_HOUSE, PublicationComparator.comparingPublishingHouse());
        add(SpecificationName.BY_YEAR_OF_PUBLISHING, PublicationComparator.comparingYearOfPublishing());
        add(SpecificationName.BY_MAGAZINE_TYPE, MagazineComparator.comparingMagazineType());

    }

    public static MagazineComparatorMap getInstance() {
        return INSTANCE;
    }

    private void add(SpecificationName specificationName, Comparator<? super Magazine> comparator) {
        comparators.put(specificationName, comparator);
    }

    public Collection<SpecificationName> getSpecificationsNames() {
        return comparators.keySet();
    }

    public Comparator<? super Magazine> get(SpecificationName name) {
        return comparators.get(name);
    }
}
