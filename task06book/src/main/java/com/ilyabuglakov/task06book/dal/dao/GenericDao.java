package com.ilyabuglakov.task06book.dal.dao;

import java.io.Serializable;

public interface GenericDao<T, S extends Sp> {

    void create(T newInstance);
    void delete(T existingObject);

}
