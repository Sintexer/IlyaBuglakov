package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.type.TestStatus;

import java.util.Optional;

public interface TestService extends Service{
    void changeTestStatus(Integer testId, TestStatus status) throws PersistentException;
    Optional<Test> getTest(Integer id) throws DaoOperationException;
}
