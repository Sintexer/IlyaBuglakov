package com.ilyabuglakov.task0201books.storage;

import com.ilyabuglakov.task0201books.bean.SpecificationName;
import com.ilyabuglakov.task0201books.dal.specification.Specification;
import com.ilyabuglakov.task0201books.dal.specification.publication.FirstLetterMatchSpecification;
import com.ilyabuglakov.task0201books.dal.specification.publication.SameIdSpecification;
import com.ilyabuglakov.task0201books.dal.specification.publication.SameNameSpecification;
import com.ilyabuglakov.task0201books.model.publication.Publication;

import java.util.Collection;
import java.util.EnumMap;

/**
 * Stores Publication specifications
 */
public class SpecificationMap {
    private static final SpecificationMap INSTANCE = new SpecificationMap();
    private EnumMap<SpecificationName, Specification<? super Publication>> specifications =
            new EnumMap<>(SpecificationName.class);


    private SpecificationMap() {
        add(SpecificationName.BY_NAME, new SameNameSpecification());
        add(SpecificationName.BY_ID, new SameIdSpecification());
        add(SpecificationName.BY_FIRST_LETTER, new FirstLetterMatchSpecification());
    }

    public static SpecificationMap getInstance() {
        return INSTANCE;
    }

    private void add(SpecificationName specificationName, Specification<? super Publication> specification) {
        specifications.put(specificationName, specification);
    }

    public Collection<SpecificationName> getSpecificationsNames() {
        return specifications.keySet();
    }

    public Specification<? super Publication> get(SpecificationName name) {
        return specifications.get(name);
    }
}
