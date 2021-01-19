package com.ilyabuglakov.raise.command.test;

import com.google.gson.Gson;
import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.command.exception.TestNotFoundException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.dto.TestDto;
import com.ilyabuglakov.raise.model.dto.TestResultDto;
import com.ilyabuglakov.raise.model.service.domain.test.TestDatabaseReadService;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestReadService;
import com.ilyabuglakov.raise.model.service.test.TestResultService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

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
        if (body == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Gson gson = new Gson();
        TestDto testDto = gson.fromJson(body, TestDto.class);
        log.info(testDto);
        log.info(testDto.getQuestions());

        Optional<TestResultDto> testResultDtoOptional = Optional.empty();

        try (Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {

            TestReadService testReadService = new TestDatabaseReadService(transaction);
            Optional<Test> test = testReadService.getTest(testDto.getId());
            if(!test.isPresent())
                throw new TestNotFoundException("Test wasn't found");

            TestResultDto testResultDto = TestResultService.getInstance().createResult(testDto, test.get());
            log.info(testResultDto);
            //TODO save results to db
            request.setAttribute("testResult", testResultDto);
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("test.testing.result"))
                .forward(request, response);
        } catch (Exception e) {
            log.error("Can't close transaction", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
//
//        request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("test.creator.save.success"))
//                .forward(request, response);

    }
}
