package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.UserTestResult;

import java.util.Optional;

public interface UserTestResultDao extends Dao<UserTestResult> {
//    boolean exists(UserTestResult userTestResult) throws DaoOperationException;
    Optional<UserTestResult> getByUserIdAndTestId(Integer userId, Integer testId) throws DaoOperationException;
}
