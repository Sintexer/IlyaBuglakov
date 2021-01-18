package com.ilyabuglakov.raise.command.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.TestInfo;
import com.ilyabuglakov.raise.model.service.domain.test.TestDatabaseReadService;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestReadService;
import com.ilyabuglakov.raise.model.service.test.TestCatalogService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Log4j2
public class TestingGetCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        Integer testId = null;
        try {
            testId = Integer.parseInt(request.getParameter("testId"));
        }catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Optional<Test> test= null;
        try(Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            TestReadService testReadService = new TestDatabaseReadService(transaction);
            test = testReadService.getTest(testId);
        } catch (DaoOperationException e) {
            log.error("Error while reading tests from db", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (Exception e) {
            log.fatal("Error while closing transaction");
        }

        if(test.isPresent()){
            log.info(test.get());
            request.setAttribute("test", test.get());
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("test.testing"))
                    .forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


    }
}
