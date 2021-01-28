package com.ilyabuglakov.raise.model.service.domain.utils.test;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.service.domain.TransactionWebService;
import com.ilyabuglakov.raise.model.service.domain.utils.test.interfaces.TestReadService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestDatabaseReadService extends TransactionWebService implements TestReadService {
    public TestDatabaseReadService(Transaction transaction) {
        super(transaction);
    }

    @Override
    public Integer getTestAmount(Integer authorId) throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        return testDao.findTestAmount(authorId);
    }

}
