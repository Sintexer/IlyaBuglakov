package com.ilyabuglakov.raise.model.service.domain.database;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.service.domain.TestService;

import java.util.Optional;

public class TestDatabaseService extends DatabaseService implements TestService {
    public TestDatabaseService(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void changeTestStatus(Integer testId, TestStatus status) throws PersistentException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        testDao.updateStatus(testId, status);
    }

    @Override
    public Optional<Test> getTest(Integer id) throws DaoOperationException {
        return ((TestDao) transaction.createDao(DaoType.TEST))
                .read(id);
    }
}
