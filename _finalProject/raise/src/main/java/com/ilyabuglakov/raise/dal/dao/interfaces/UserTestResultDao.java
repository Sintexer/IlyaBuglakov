package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.UserTestResult;

import java.util.List;
import java.util.Optional;

public interface UserTestResultDao extends Dao<UserTestResult> {
    Optional<UserTestResult> getByUserIdAndTestId(Integer userId, Integer testId) throws DaoOperationException;

    int getResultAmount(Integer userId) throws DaoOperationException;

    List<UserTestResult> getUserTestResults(Integer userId) throws DaoOperationException;
}
