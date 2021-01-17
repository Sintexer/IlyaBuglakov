package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Test;

import java.util.Optional;

public interface TestDao extends Dao<Test> {
    Optional<Integer> getTestId(String testName) throws DaoOperationException;
}
