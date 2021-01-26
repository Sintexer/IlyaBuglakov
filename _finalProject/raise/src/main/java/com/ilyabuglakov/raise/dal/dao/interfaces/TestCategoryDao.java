package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.TestCategory;

import java.util.List;

public interface TestCategoryDao extends Dao<TestCategory> {
    List<TestCategory> findAll()throws DaoOperationException;
}
