package com.ilyabuglakov.raise.command.test;

import com.google.gson.Gson;
import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.UserTestResult;
import com.ilyabuglakov.raise.model.dto.TestDto;
import com.ilyabuglakov.raise.model.dto.TestResultDto;
import com.ilyabuglakov.raise.model.service.RequestService;
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
public class TestResultCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String body = request.getParameter("testJson");
        log.info(body);
        if (body == null) {
            RequestService.getInstance().setRequestErrorAttributes(request, "error.400", 400);
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("error"))
                    .forward(request, response);
            return;
        }

        Gson gson = new Gson();
        TestDto testDto = gson.fromJson(body, TestDto.class);
        log.info(testDto);
        log.info(testDto.getQuestions());

        Optional<TestResultDto> testResultDtoOptional = Optional.empty();

        try (Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {

            TestReadService testReadService = new TestDatabaseReadService(transaction);
            Optional<Test> testOptional = testReadService.getTest(testDto.getId());
            if (!testOptional.isPresent()) {
                RequestService.getInstance().setRequestErrorAttributes(request, "error.404", 404);
                request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("error"))
                        .forward(request, response);
                return;
            }

            TestResultDto testResultDto = TestResultService.getInstance().createResult(testDto, testOptional.get());
            log.info(testResultDto);
            //TODO divide method

            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                UserSearchService userSearchService = new UserTransactionSearch(transaction);
                Optional<User> user = userSearchService.findByEmail((String) SecurityUtils.getSubject().getPrincipal());
                if (!user.isPresent()) {
                    RequestService.getInstance().setRequestErrorAttributes(request, "error.db", 500);
                    request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("error"))
                            .forward(request, response);
                    return;
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

            request.setAttribute("testName", testOptional.get().getTestName());
            request.setAttribute("testResult", testResultDto);
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("test.testing.result"))
                    .forward(request, response);
        } catch (Exception e) {
            log.error("Can't close transaction", e);
            RequestService.getInstance().setRequestErrorAttributes(request, "error.db", 500);
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("error"))
                    .forward(request, response);
        }


    }
}
