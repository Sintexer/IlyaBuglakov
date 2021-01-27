package com.ilyabuglakov.raise.model.service.domain.utils.test;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.AnswerDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.QuestionDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.Answer;
import com.ilyabuglakov.raise.domain.Question;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.dto.TestInfo;
import com.ilyabuglakov.raise.model.service.domain.TransactionWebService;
import com.ilyabuglakov.raise.model.service.domain.utils.test.interfaces.TestReadService;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
public class TestDatabaseReadService extends TransactionWebService implements TestReadService {
    public TestDatabaseReadService(Transaction transaction) {
        super(transaction);
    }

    @Override
    public Optional<Test> getTest(Integer testId) throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        QuestionDao questionDao = (QuestionDao) transaction.createDao(DaoType.QUESTION);
        AnswerDao answerDao = (AnswerDao) transaction.createDao(DaoType.ANSWER);
        UserDao userDao = (UserDao) transaction.createDao(DaoType.USER);

        Optional<Test> test = testDao.read(testId);
        if (test.isPresent()) {
            test.get().setCharacteristics(testDao.getCharacteristics(testId));
            test.get().setAuthor(userDao.read(test.get().getId()).orElse(null));
            Set<Question> questions = questionDao.findByTestId(testId);
            for (Question question : questions) {
                Set<Answer> answers = answerDao.findByQuestionId(question.getId());
                question.setAnswers(answers);
                question.setCorrectAmount((int) answers.stream().filter(Answer::isCorrect).count());
            }
            test.get().setQuestions(questions);
        }
        return test;
    }

    @Override
    public List<TestInfo> getNewTestInfos(int pageNumber, int testsPerPage) throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        QuestionDao questionDao = (QuestionDao) transaction.createDao(DaoType.QUESTION);
        UserDao userDao = (UserDao) transaction.createDao(DaoType.USER);

        List<Test> tests = testDao.getNewTests(pageNumber * testsPerPage, testsPerPage);
        List<TestInfo> testInfos = new ArrayList<>();
        for (Test test : tests) {
            Set<Characteristic> characteristicSet = testDao.getCharacteristics(test.getId());
            int questionsAmount = questionDao.getQuestionAmount(test.getId()).orElseThrow(DaoOperationException::new);
            //todo only name and id
            User author = userDao.read(test.getAuthor().getId()).orElse(null);
            String questionNames = questionDao.getQuestionsNames(test.getId()).stream().collect(Collectors.joining("; "));
            log.debug(test.getAuthor().getId() + ": " + author);
            testInfos.add(TestInfo.builder()
                    .testName(test.getTestName())
                    .author(author)
                    .characteristics(characteristicSet)
                    .difficulty(test.getDifficulty())
                    .id(test.getId())
                    .build());
        }
        return testInfos;
    }

    @Override
    public List<TestInfo> getTestInfos(int pageNumber, int testsPerPage) throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        QuestionDao questionDao = (QuestionDao) transaction.createDao(DaoType.QUESTION);
        UserDao userDao = (UserDao) transaction.createDao(DaoType.USER);

        List<Test> tests = testDao.getTests(pageNumber * testsPerPage, testsPerPage);
        List<TestInfo> testInfos = new ArrayList<>();
        for (Test test : tests) {
            Set<Characteristic> characteristicSet = testDao.getCharacteristics(test.getId());
            int questionsAmount = questionDao.getQuestionAmount(test.getId()).orElseThrow(DaoOperationException::new);
            User author = userDao.read(test.getAuthor().getId()).orElse(null);
            String questionNames = String.join("; ", questionDao.getQuestionsNames(test.getId()));
            log.debug(test.getAuthor().getId() + ": " + author);
            testInfos.add(TestInfo.builder()
                    .testName(test.getTestName())
                    .author(author)
                    .characteristics(characteristicSet)
                    .difficulty(test.getDifficulty())
                    .id(test.getId())
                    .build());
        }
        return testInfos;

    }

    @Override
    public Integer getTestAmount() throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        return testDao.getTestAmount();
    }

    @Override
    public Integer getTestAmount(Integer authorId) throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        return testDao.getTestAmount(authorId);
    }

    @Override
    public Integer getNewTestAmount() throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        return testDao.getNewTestAmount();
    }

    @Override
    public Integer getNewTestAmount(Integer authorId) throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        return testDao.getNewTestAmount(authorId);
    }
}
