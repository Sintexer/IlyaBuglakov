package com.ilyabuglakov.raise.model.service.domain.database.test;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.AnswerDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.QuestionDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestCategoryDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserDao;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.Question;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.TestCategory;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.dto.AdvancedTestInfo;
import com.ilyabuglakov.raise.model.dto.TestDto;
import com.ilyabuglakov.raise.model.dto.TestInfo;
import com.ilyabuglakov.raise.model.dto.TestResultDto;
import com.ilyabuglakov.raise.model.dto.TestSearchParametersDto;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.TestService;
import com.ilyabuglakov.raise.model.service.domain.database.DatabaseService;
import com.ilyabuglakov.raise.model.service.test.TestResultService;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
public class TestDatabaseService extends DatabaseService implements TestService {
    public TestDatabaseService(Transaction transaction) {
        super(transaction);
    }

    @Override
    public ResponseEntity createResult(TestDto testDto) throws PersistentException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        Optional<Test> test = getTest(testDto.getId());
        ResponseEntity responseEntity = new ResponseEntity();
        if(test.isPresent()){
            TestResultDto testResultDto = TestResultService.getInstance().createResult(testDto, test.get());
            responseEntity.setAttribute("testName", test.get().getTestName());
            responseEntity.setAttribute("testResult", testResultDto);
            return responseEntity;
        }
        responseEntity.setErrorOccurred(true);
        return responseEntity;
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
    public Optional<TestDto> createDtoFromJson(String json) {
        Gson gson = new Gson();
        Optional<TestDto> testDto = Optional.empty();
        try {
            testDto = Optional.of(gson.fromJson(json, TestDto.class));
        } catch (JsonParseException e) {
            log.error("Illegal test object json format " + json, e);
            //Exception is ignored because caller method will operate Optional Test object and will see its empty
        }
        return testDto;
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
        test.setDifficulty(Test.BASE_DIFFICULTY);
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
        transaction.commit();
    }

    @Override
    public void changeTestStatus(Integer testId, TestStatus status) throws PersistentException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        testDao.updateStatus(testId, status);
        transaction.commit();
    }

    @Override
    public Optional<Test> getTest(Integer id) throws DaoOperationException {
        TestInfoDatabaseService testInfoDatabaseService = new TestInfoDatabaseService(transaction);
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        return testInfoDatabaseService.fillTest(testDao.read(id));
    }

    @Override
    public List<TestInfo> findBySearchParameters(TestSearchParametersDto dto) throws PersistentException {
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
        TestInfoDatabaseService infoService = new TestInfoDatabaseService(transaction);
        TestCategoryDao categoryDao = (TestCategoryDao) transaction.createDao(DaoType.TEST_CATEGORY);
        List<Test> tests;
        if(!dto.getTestName().isEmpty() && dto.getCategory()!=null){
            TestCategory testCategory = categoryDao.read(dto.getCategory().getId()).orElseThrow(PersistentException::new);
            if(testCategory.getParent()==null)
                tests =  testDao.findByNameAndParentCategoryAndStatus(
                        dto.getTestName(),dto.getCategory(), dto.getStatus(), dto.getLimit(), dto.getPage());
            else
                tests = testDao.findByNameAndCategoryAndStatus(
                        dto.getTestName(),dto.getCategory(), dto.getStatus(), dto.getLimit(), dto.getPage());
        } else if(!dto.getTestName().isEmpty()){
            tests = testDao.findByNameAndStatus(dto.getTestName(), dto.getStatus(), dto.getLimit(), dto.getPage());
        } else {
            TestCategory testCategory = categoryDao.read(dto.getCategory().getId()).orElseThrow(PersistentException::new);
            if(testCategory.getParent()==null)
                tests =  testDao.findByParentCategoryAndStatus(
                        dto.getCategory(), dto.getStatus(), dto.getLimit(), dto.getPage());
            else
                tests = testDao.findByCategoryAndStatus(
                        dto.getCategory(), dto.getStatus(), dto.getLimit(), dto.getPage());
        }
        return infoService.getTestInfos(tests);
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
    public int getTestAmountByStatus(TestStatus status) throws PersistentException {
        return ((TestDao) transaction.createDao(DaoType.TEST))
                .findTestAmountByStatus(status);
    }

}
