package com.ilyabuglakov.raise.model.service.domain.test;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.AnswerDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.QuestionDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.Answer;
import com.ilyabuglakov.raise.domain.Question;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.dto.TestInfo;
import com.ilyabuglakov.raise.model.service.domain.TransactionWebService;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestReadService;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        Optional<Test> test = testDao.read(testId);
        if(test.isPresent()){
            test.get().setCharacteristics(testDao.getCharacteristics(testId));
          Set<Question> questions = questionDao.findByTestId(testId);
          for(Question question : questions){
              Set<Answer> answers = answerDao.findByQuestionId(question.getId());
              question.setAnswers(answers);
              question.setCorrectAmount((int)answers.stream().filter(Answer::isCorrect).count());
          }
          test.get().setQuestions(questions);
        }
        return test;
    }

    @Override
    public List<TestInfo> getTestInfos(int pageNumber, int testsPerPage) throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        QuestionDao questionDao = (QuestionDao) transaction.createDao(DaoType.QUESTION);

        List<Test> tests = testDao.getTests(pageNumber * testsPerPage, testsPerPage);
        List<TestInfo> testInfos = new ArrayList<>();
        for (Test test : tests) {
            Set<Characteristic> characteristicSet = testDao.getCharacteristics(test.getId());
            int questionsAmount = questionDao.getQuestionAmount(test.getId()).orElseThrow(DaoOperationException::new);
            testInfos.add(TestInfo.builder()
                    .testName(test.getTestName())
                    .characteristics(characteristicSet)
                    .difficulty(test.getDifficulty())
                    .id(test.getId())
                    .questionsAmount(questionsAmount)
                    .build());
        }
        return testInfos;

    }

    @Override
    public Integer getTestAmount() throws DaoOperationException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        return testDao.getTestAmount();
    }
}
