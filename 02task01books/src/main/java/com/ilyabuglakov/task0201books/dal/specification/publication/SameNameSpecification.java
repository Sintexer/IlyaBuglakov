package com.ilyabuglakov.task0201books.dal.specification.publication;

import com.ilyabuglakov.task0201books.model.publication.Publication;

public class SameNameSpecification implements PublicationSpecification {

    private String name;

    public void setName(String name){
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(Publication criteria) {
        return name.equals(criteria.getName());
    }
}
