package com.ilyabuglakov.raise.command.test;

import com.google.gson.Gson;
import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.ValidationCommands;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.service.web.test.TestDatabaseSaveService;
import com.ilyabuglakov.raise.model.service.web.test.exception.TestSaveServiceException;
import com.ilyabuglakov.raise.model.service.web.test.interfaces.TestSaveService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class TestCreatorSaveCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {


        String body = request.getParameter("testJson");
        log.info(body);

        Gson gson = new Gson();
        Test test = gson.fromJson(body, Test.class);

        try (Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            boolean isValid = ValidationCommands.TEST_VALIDATION.getCommand().execute(test, request);
            if (!isValid) {
                request.setAttribute("testWasntCreated", true);
                request.getRequestDispatcher(PropertiesStorage.getInstance()
                        .getPages()
                        .getProperty("test.creator.save.failure"))
                .forward(request, response);
            }
            TestSaveService testSaveService = new TestDatabaseSaveService(transaction);
            testSaveService.save(test);
            transaction.commit();
            request.setAttribute("testName", test.getTestName());
        } catch (TestSaveServiceException e) {
            request.setAttribute("dbError", true);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (Exception e) {
            log.error("Can't close transaction", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("test.creator.save.success"))
                .forward(request, response);


    }

}
