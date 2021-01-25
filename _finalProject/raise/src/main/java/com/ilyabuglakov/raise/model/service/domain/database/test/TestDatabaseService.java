package com.ilyabuglakov.raise.model.service.domain.database.test;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.AnswerDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.QuestionDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserDao;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.Question;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.dto.AdvancedTestInfo;
import com.ilyabuglakov.raise.model.dto.TestInfo;
import com.ilyabuglakov.raise.model.service.domain.TestService;
import com.ilyabuglakov.raise.model.service.domain.database.DatabaseService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Log4j2
public class TestDatabaseService extends DatabaseService implements TestService {
    public TestDatabaseService(Transaction transaction) {
        super(transaction);
    }

    @Override
    public Optional<Test> createFromJson(String json) {
        Gson gson = new Gson();
        Optional<Test> test = Optional.empty();
        try {
            test = Optional.of(gson.fromJson(json, Test.class));
        } catch (JsonParseException e) {
            log.error("Illegal test object json format " + json, e);
            //Exception is ignored because caller method will operate Optional Test object and will see its empty
        }
        return test;
    }

    @Override
    public void save(Test test, String authorEmail) throws PersistentException {
        UserDao userDao = (UserDao) transaction.createDao(DaoType.USER);
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        QuestionDao questionDao = (QuestionDao) transaction.createDao(DaoType.QUESTION);
        AnswerDao answerDao = (AnswerDao) transaction.createDao(DaoType.ANSWER);

        User user = userDao.findByEmail(authorEmail).orElse(null);
        test.setAuthor(user);
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
    }

    @Override
    public void changeTestStatus(Integer testId, TestStatus status) throws PersistentException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        testDao.updateStatus(testId, status);
    }

    @Override
    public Optional<Test> getTest(Integer id) throws DaoOperationException {
        TestInfoDatabaseService testInfoDatabaseService = new TestInfoDatabaseService(transaction);
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        return testInfoDatabaseService.fillTest(testDao.read(id));
    }

    @Override
    public List<TestInfo> getTestInfosByStatus(TestStatus status, int limit, int from) throws DaoOperationException {
        TestSearchDatabaseService searchService = new TestSearchDatabaseService(transaction);
        TestInfoDatabaseService infoService = new TestInfoDatabaseService(transaction);
        return infoService.getTestInfos(searchService.getTests(status, limit, from));
    }

    @Override
    public List<TestInfo> getTestInfosByStatusAndPage(TestStatus status, int limit, int from) throws PersistentException {
        return getTestInfosByStatus(status, limit, from * limit);
    }

    @Override
    public List<AdvancedTestInfo> getAdvancedTestInfosByStatus(TestStatus status, int limit, int from) throws PersistentException {
        TestSearchDatabaseService searchService = new TestSearchDatabaseService(transaction);
        TestInfoDatabaseService infoService = new TestInfoDatabaseService(transaction);
        return infoService.getAdvancedTestInfo(searchService.getTests(status, limit, from));
    }

    @Override
    public List<AdvancedTestInfo> getAdvancedTestInfosByStatusAndPage(TestStatus status, int limit, int from) throws PersistentException {
        return getAdvancedTestInfosByStatus(status, limit, from * limit);
    }

    @Override
    public List<TestInfo> getTestInfos(int limit, int from) throws DaoOperationException {
        TestSearchDatabaseService searchService = new TestSearchDatabaseService(transaction);
        TestInfoDatabaseService infoService = new TestInfoDatabaseService(transaction);
        return infoService.getTestInfos(searchService.getTests(TestStatus.CONFIRMED, limit, from));
    }

    @Override
    public List<TestInfo> getTestInfosByPage(int limit, int from) throws PersistentException {
        return getTestInfos(limit, from * limit);
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
    public int getTestAmountByStatus(TestStatus status) throws PersistentException {
        return ((TestDao) transaction.createDao(DaoType.TEST))
                .getTestAmountByStatus(status);
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
    public int getTestAmountByStatus(TestStatus status, Integer authorId) throws PersistentException {
        return ((TestDao) transaction.createDao(DaoType.TEST))
                .getTestAmountByStatus(status, authorId);
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
