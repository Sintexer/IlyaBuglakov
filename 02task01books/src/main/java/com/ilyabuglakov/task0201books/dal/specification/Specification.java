package com.ilyabuglakov.task0201books.dal.specification;

public interface Specification<T> {

    boolean isSatisfiedBy(T criteria);

    default Specification<T> and(Specification<T> another) {
        return new AndSpecification<>(this, another);
    }

    default Specification<T> or(Specification<T> another) {
        return new OrSpecification<>(this, another);
    }

    default Specification<T> not() {
        return new NotSpecification<>(this);
    }

}
