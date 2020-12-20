package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
    long create(T entity) throws DaoOperationException;
    T read(long id) throws  DaoOperationException;
    List<T> readAll() throws DaoOperationException;
    void update(T entity) throws DaoOperationException;
    void delete(T entity) throws DaoOperationException;
}
