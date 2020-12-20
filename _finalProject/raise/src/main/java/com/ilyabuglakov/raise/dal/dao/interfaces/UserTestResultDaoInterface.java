package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.UserTestResult;

import java.util.List;

public interface UserTestResultDaoInterface extends Dao<UserTestResult> {
    List<UserTestResult> readAll() throws DaoOperationException;
}
