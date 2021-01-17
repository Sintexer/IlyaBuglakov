package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Test;

import java.util.List;
import java.util.Optional;

public interface TestDao extends Dao<Test> {
    List<Test> getTests(int startFrom, int itemsAmount) throws DaoOperationException;
}
