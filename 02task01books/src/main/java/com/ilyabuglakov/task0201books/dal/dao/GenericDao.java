package com.ilyabuglakov.task0201books.dal.dao;

import com.ilyabuglakov.task0201books.dal.specification.Specification;
import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.exception.DaoRemoveException;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

    void add(T newInstance) throws DaoAddException;

    void remove(T existingObject) throws DaoRemoveException;

    void clear();

    List<T> getAll();

    void setAll(List<T> content);

    <S extends Specification<T>> Optional<T> findByCriteria(S criteria);

    <S extends Specification<T>> List<T> findAllByCriteria(S criteria);

}
