package com.ilyabuglakov.task06book.dal.specification;

public class OrSpecification<T> implements Specification<T> {

    private Specification<T> first;
    private Specification<T> second;

    public OrSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfiedBy(T criteria){
        return first.isSatisfiedBy(criteria) || second.isSatisfiedBy(criteria);
    }

}
