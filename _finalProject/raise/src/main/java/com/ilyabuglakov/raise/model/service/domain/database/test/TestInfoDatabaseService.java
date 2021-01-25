package com.ilyabuglakov.raise.model.service.domain.database.test;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.QuestionDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.dto.AdvancedTestInfo;
import com.ilyabuglakov.raise.model.dto.TestInfo;
import com.ilyabuglakov.raise.model.service.domain.database.DatabaseService;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Log4j2
public class TestInfoDatabaseService extends DatabaseService {

    public TestInfoDatabaseService(Transaction transaction) {
        super(transaction);
    }

    public List<TestInfo> getTestInfos(List<Test> tests) throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        QuestionDao questionDao = (QuestionDao) transaction.createDao(DaoType.QUESTION);
        UserDao userDao = (UserDao) transaction.createDao(DaoType.USER);
        List<TestInfo> testInfos = new ArrayList<>();
        for (Test test : tests) {
            Set<Characteristic> characteristicSet = testDao.getCharacteristics(test.getId());
            int questionsAmount = questionDao.getQuestionAmount(test.getId()).orElseThrow(DaoOperationException::new);
            User authorInfo = userDao.findUserInfo(test.getAuthor().getId()).orElse(null);
            testInfos.add(TestInfo.builder()
                    .testName(test.getTestName())
                    .author(authorInfo)
                    .characteristics(characteristicSet)
                    .difficulty(test.getDifficulty())
                    .id(test.getId())
                    .questionsAmount(questionsAmount)
                    .build());
        }
        return testInfos;
    }

    public List<AdvancedTestInfo> getAdvancedTestInfo(List<Test> tests) throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        QuestionDao questionDao = (QuestionDao) transaction.createDao(DaoType.QUESTION);
        UserDao userDao = (UserDao) transaction.createDao(DaoType.USER);

        List<AdvancedTestInfo> testInfos = new ArrayList<>();
        for (Test test : tests) {
            Set<Characteristic> characteristicSet = testDao.getCharacteristics(test.getId());
            int questionsAmount = questionDao.getQuestionAmount(test.getId()).orElseThrow(DaoOperationException::new);
            User author = userDao.read(test.getAuthor().getId()).orElse(null);
            String questionNames = String.join("; ", questionDao.getQuestionsNames(test.getId()));
            log.debug(test.getAuthor().getId() + ": " + author);
            testInfos.add(AdvancedTestInfo.builder()
                    .testName(test.getTestName())
                    .author(author)
                    .characteristics(characteristicSet)
                    .difficulty(test.getDifficulty())
                    .id(test.getId())
                    .questionNames(questionNames)
                    .questionsAmount(questionsAmount)
                    .build());
        }
        return testInfos;
    }

}
