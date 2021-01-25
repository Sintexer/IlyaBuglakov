package com.ilyabuglakov.raise.model.service.domain.database.test;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.dto.TestInfo;
import com.ilyabuglakov.raise.model.service.domain.TestService;
import com.ilyabuglakov.raise.model.service.domain.database.DatabaseService;

import java.util.List;
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

    @Override
    public List<TestInfo> getNewTestInfos(int limit, int from) throws DaoOperationException {
        TestSearchDatabaseService searchService = new TestSearchDatabaseService(transaction);
        TestInfoDatabaseService infoService = new TestInfoDatabaseService(transaction);
        return infoService.getTestInfos(searchService.getTests(TestStatus.NEW, limit, from));
    }

    @Override
    public List<TestInfo> getNewTestInfosByPage(int limit, int from) throws PersistentException {
        return getNewTestInfos(limit, from * limit);
    }

    @Override
    public int getTestAmount() throws PersistentException {
        return ((TestDao) transaction.createDao(DaoType.TEST))
                .getTestAmount();
    }

    @Override
    public int getNewTestAmount() throws PersistentException {
        return ((TestDao) transaction.createDao(DaoType.TEST))
                .getNewTestAmount();
    }

    @Override
    public int getTestAmount(Integer authorId) throws PersistentException {
        return ((TestDao) transaction.createDao(DaoType.TEST))
                .getTestAmount(authorId);
    }

    @Override
    public int getNewTestAmount(Integer authorId) throws PersistentException {
        return ((TestDao) transaction.createDao(DaoType.TEST))
                .getNewTestAmount(authorId);
    }
}
