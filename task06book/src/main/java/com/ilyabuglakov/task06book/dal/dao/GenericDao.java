package com.ilyabuglakov.task06book.dal.dao;

import com.ilyabuglakov.task06book.dal.specification.Specification;
import com.ilyabuglakov.task06book.exception.DaoRemoveException;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

    void add(T newInstance);
    void remove(T existingObject) throws DaoRemoveException;
    List<T> getAll();
    <S extends Specification<T>> Optional<T> findByCriteria(S criteria);
    <S extends Specification<T>> List<T> findAllByCriteria(S criteria);

}
