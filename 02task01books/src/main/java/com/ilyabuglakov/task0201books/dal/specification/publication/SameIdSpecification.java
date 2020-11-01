package com.ilyabuglakov.task0201books.dal.specification.publication;

import com.ilyabuglakov.task0201books.model.publication.Publication;

public class SameIdSpecification implements PublicationSpecification {

    private long id;

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean isSatisfiedBy(Publication criteria) {
        return id == criteria.getId();
    }
}
