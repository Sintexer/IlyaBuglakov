package com.ilyabuglakov.task06book.storage;

import com.ilyabuglakov.task06book.bean.SpecificationName;
import com.ilyabuglakov.task06book.dal.specification.Specification;
import com.ilyabuglakov.task06book.dal.specification.book.SameAuthorsSpecification;
import com.ilyabuglakov.task06book.dal.specification.book.SameBookSpecification;
import com.ilyabuglakov.task06book.dal.specification.book.SameNameSpecification;
import com.ilyabuglakov.task06book.model.book.Book;

import java.util.Collection;
import java.util.EnumMap;

public class SpecificationMap {
    private static final SpecificationMap INSTANCE = new SpecificationMap();
    private EnumMap<SpecificationName, Specification<Book>> specifications =
            new EnumMap<>(SpecificationName.class);


    private SpecificationMap(){
        add(SpecificationName.BY_NAME, new SameNameSpecification());
        add(SpecificationName.BY_AUTHORS, new SameAuthorsSpecification());
        add(SpecificationName.BY_NAME, new SameNameSpecification());
        add(SpecificationName.BY_NAME, new SameNameSpecification());
        add(SpecificationName.BY_NAME, new SameNameSpecification());
        add(SpecificationName.BY_ALL, new SameBookSpecification());
    }

    public static SpecificationMap getInstance() {
        return INSTANCE;
    }

    private void add(SpecificationName specificationName, Specification<Book> specification){
        specifications.put(specificationName, specification);
    }

    public Collection<SpecificationName> getSpecificationsNames(){
        return specifications.keySet();
    }

    public Specification<Book> get(SpecificationName name){
        return specifications.get(name);
    }
}
