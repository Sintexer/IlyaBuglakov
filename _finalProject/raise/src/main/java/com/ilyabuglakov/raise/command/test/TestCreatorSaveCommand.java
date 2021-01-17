package com.ilyabuglakov.raise.command.test;

import com.google.gson.Gson;
import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.ValidationCommands;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.exception.TransactionException;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.service.web.test.TestDatabaseSaveService;
import com.ilyabuglakov.raise.model.service.web.test.exception.TestSaveServiceException;
import com.ilyabuglakov.raise.model.service.web.test.interfaces.TestSaveService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class TestCreatorSaveCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {

        String body = request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
        log.info(body);

        String errorAttributeName = "testError";

        Gson gson = new Gson();
        Test test = gson.fromJson(body, Test.class);

        try(Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            boolean isValid = ValidationCommands.TEST_VALIDATION.getCommand().execute(test, request);
            if (!isValid) {
                request.setAttribute(errorAttributeName, "Some of test fields are not valid. Check if all questions have at least one correct answer");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);
            Optional<Integer> testId = testDao.getTestId(test.getTestName());
            if(testId.isPresent()){
                request.setAttribute(errorAttributeName, "Test already exists");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            TestSaveService testSaveService = new TestDatabaseSaveService(transaction);
            testSaveService.save(test);
            transaction.commit();
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (TestSaveServiceException e) {
            request.setAttribute(errorAttributeName, "Error while saving test to db");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (Exception e) {
            log.error("Can't close transaction", e);
            return;
        }

        response.setContentType("text");
        response.getWriter().print(PropertiesStorage.getInstance().getLinks().getProperty("index"));


    }

}
