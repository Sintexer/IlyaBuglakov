package com.ilyabuglakov.task0201books.dal.dao;

import com.ilyabuglakov.task0201books.dal.specification.Specification;
import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.exception.DaoRemoveException;
import com.ilyabuglakov.task0201books.exception.DaoWrongTypeException;
import com.ilyabuglakov.task0201books.model.publication.Publication;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T extends Publication> {

    void add(Publication newInstance) throws DaoAddException;

    T get(int index);

    void set(int index, Publication newInstance) throws DaoWrongTypeException;

    void remove(Publication existingObject) throws DaoRemoveException;

    void clear();

    List<T> getAll();

    void setAll(List<T> content);

    int indexOf(Publication o) throws DaoWrongTypeException;

    <S extends Specification<T>> Optional<T> findByCriteria(S criteria);

    <S extends Specification<T>> List<T> findAllByCriteria(S criteria);

    public void sortBy(Comparator<? super T> comparator);

}
