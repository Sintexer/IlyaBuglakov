package com.ilyabuglakov.raise.dal.dao;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Entity;

import java.util.Optional;


public interface Dao<T extends Entity> {
    Integer create(T entity) throws DaoOperationException;

    Optional<T> read(Integer id) throws DaoOperationException;

    void update(T entity) throws DaoOperationException;

    void delete(T entity) throws DaoOperationException;
}
