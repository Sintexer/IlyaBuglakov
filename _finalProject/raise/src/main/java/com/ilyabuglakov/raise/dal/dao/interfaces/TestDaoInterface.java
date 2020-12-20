package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Test;

import java.util.List;

public interface TestDaoInterface extends Dao<Test> {
    List<Test> readAll() throws DaoOperationException;
}
