package com.ilyabuglakov.raise.model.service.domain.test;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.AnswerDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.QuestionDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.exception.TransactionException;
import com.ilyabuglakov.raise.domain.Question;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.service.domain.TransactionWebService;
import com.ilyabuglakov.raise.model.service.domain.test.exception.TestSaveServiceException;
import com.ilyabuglakov.raise.model.service.domain.test.exception.TestSaveServiceLimitException;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestSaveService;
import com.ilyabuglakov.raise.model.service.property.ApplicationProperties;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestDatabaseSaveService extends TransactionWebService implements TestSaveService {

    public TestDatabaseSaveService(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void save(Test test) throws TestSaveServiceException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        QuestionDao questionDao = (QuestionDao) transaction.createDao(DaoType.QUESTION);
        AnswerDao answerDao = (AnswerDao) transaction.createDao(DaoType.ANSWER);


        try {
            Integer newTestAmount = testDao.getNewTestAmount(test.getAuthor().getId());
            if (newTestAmount >= Integer.parseInt(ApplicationProperties.getProperty("user.max.new.tests"))) {
                throw new TestSaveServiceLimitException("User reached new tests limit");
            }

            if (test.getStatus() == null)
                test.setStatus(TestStatus.NEW);
            Integer testId = testDao.create(test);

            testDao.saveCharacteristics(test.getCharacteristics(), testId);

            Test testProxy = new Test();
            testProxy.setId(testId);
            test.getQuestions().forEach(question -> question.setTest(testProxy));

            for (Question question : test.getQuestions()) {
                Integer questionId = questionDao.create(question);
                Question questionProxy = new Question();
                questionProxy.setId(questionId);
                question.getAnswers().forEach(answer -> answer.setQuestion(questionProxy));
                answerDao.createAll(question.getAnswers());
            }
        } catch (DaoOperationException e) {
            try {
                transaction.rollback();
            } catch (TransactionException transactionException) {
                log.error("Can't rollback transaction", e);
            }
            throw new TestSaveServiceException("Can't save test", e);
        }
    }
}
