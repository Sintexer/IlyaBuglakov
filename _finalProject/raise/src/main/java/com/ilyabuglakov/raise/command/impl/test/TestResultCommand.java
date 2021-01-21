package com.ilyabuglakov.raise.command.impl.test;

import com.google.gson.Gson;
import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.UserTestResult;
import com.ilyabuglakov.raise.model.dto.TestDto;
import com.ilyabuglakov.raise.model.dto.TestResultDto;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.test.TestDatabaseReadService;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestReadService;
import com.ilyabuglakov.raise.model.service.domain.user.UserParametersDatabaseService;
import com.ilyabuglakov.raise.model.service.domain.user.UserTransactionSearch;
import com.ilyabuglakov.raise.model.service.domain.user.interfaces.UserParametersService;
import com.ilyabuglakov.raise.model.service.domain.user.interfaces.UserSearchService;
import com.ilyabuglakov.raise.model.service.test.TestResultService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class TestResultCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String body = request.getParameter("testJson");
        log.info(body);
        if (body == null) {
            response.sendError(400);
            return null;
        }

        Gson gson = new Gson();//TODO check exceptions
        TestDto testDto = gson.fromJson(body, TestDto.class);
        log.info(testDto);
        log.info(testDto.getQuestions());

        ResponseEntity responseEntity = new ResponseEntity();
        Optional<TestResultDto> testResultDtoOptional = Optional.empty();


        try (Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {

            TestReadService testReadService = new TestDatabaseReadService(transaction);
            Optional<Test> testOptional = testReadService.getTest(testDto.getId());
            if (!testOptional.isPresent()) {
                response.sendError(404);
                return null;
            }

            TestResultDto testResultDto = TestResultService.getInstance().createResult(testDto, testOptional.get());
            log.info(testResultDto);
            //TODO divide method

            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                UserSearchService userSearchService = new UserTransactionSearch(transaction);
                Optional<User> user = userSearchService.findByEmail((String) SecurityUtils.getSubject().getPrincipal());
                if (!user.isPresent()) {
                    response.sendError(500);
                    return null;
                }

                UserTestResult userTestResult = UserTestResult.builder()
                        .result(testResultDto.getResult())
                        .user(user.get())
                        .test(testOptional.get())
                        .build();
                UserParametersService userParametersService = new UserParametersDatabaseService(transaction);
                userParametersService.saveResult(userTestResult);
            }
            transaction.commit();

            responseEntity.setAttribute("testName", testOptional.get().getTestName());
            responseEntity.setAttribute("testResult", testResultDto);
            responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("test.testing.result"));
        } catch (PersistentException e) {
            response.sendError(500);
            return null;
        }
        return responseEntity;

    }
}
