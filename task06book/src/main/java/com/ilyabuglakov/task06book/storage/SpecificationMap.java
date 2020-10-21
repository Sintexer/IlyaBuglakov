package com.ilyabuglakov.task06book.storage;

import com.ilyabuglakov.task06book.bean.SpecificationName;
import com.ilyabuglakov.task06book.dal.specification.book.SameAuthorsSpecification;
import com.ilyabuglakov.task06book.dal.specification.book.SameNameSpecification;

import java.util.EnumMap;

public class SpecificationMap {
    private static final SpecificationMap INSTANCE = new SpecificationMap();
    private EnumMap<SpecificationName, BookSpecification> specifications =
            new EnumMap<>(SpecificationName.class);


    private SpecificationMap(){
        add(SpecificationName.BY_NAME, new SameNameSpecification());
        add(SpecificationName.BY_AUTHOR, new SameAuthorsSpecification());
        add(SpecificationName.BY_NAME, new SameNameSpecification());
        add(SpecificationName.BY_NAME, new SameNameSpecification());
        add(SpecificationName.BY_NAME, new SameNameSpecification());
    }

    public static SpecificationMap getInstance() {
        return INSTANCE;
    }

    private void add(SpecificationName specificationName, BookSpecification specification){
        specifications.put(specificationName, specification);
    }
}
