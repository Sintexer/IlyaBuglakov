package com.ilyabuglakov.raise.model.service.domain.test;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.AnswerDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.QuestionDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.service.domain.TransactionWebService;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestReadService;

import java.util.List;

public class TestDatabaseReadService extends TransactionWebService implements TestReadService {
    public TestDatabaseReadService(Transaction transaction) {
        super(transaction);
    }

    @Override
    public List<Test> getTestsInfo(int pageNumber, int testsPerPage) throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
//        QuestionDao questionDao = (QuestionDao) transaction.createDao(DaoType.QUESTION);
//        AnswerDao answerDao = (AnswerDao) transaction.createDao(DaoType.ANSWER);

        return testDao.getTests(pageNumber*testsPerPage, testsPerPage);
    }
}
