package com.ilyabuglakov.raise.command.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.service.RequestService;
import com.ilyabuglakov.raise.model.service.domain.test.TestDatabaseReadService;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestReadService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class TestPreviewPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer testId = null;
        try {
            String stringTestId = request.getParameter("testId");
            if(stringTestId!=null)
                testId = Integer.parseInt(stringTestId);
            else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Optional<Test> test = Optional.empty();
        try(Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            TestReadService testReadService = new TestDatabaseReadService(transaction);
            test = testReadService.getTest(testId);
        } catch (DaoOperationException e) {
            log.error("Error while reading tests from db", e);
            RequestService.getInstance().setRequestErrorAttributes(request, "error.db", 500);
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("error"))
                    .forward(request, response);
            return;
        } catch (Exception e) {
            log.fatal("Error while closing transaction");
        }

        if(test.isPresent() && test.get().getStatus() == TestStatus.CONFIRMED){
            log.info(test.get());
            request.setAttribute("test", test.get());
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("test.preview"))
                    .forward(request, response);
        } else {
            RequestService.getInstance().setRequestErrorAttributes(request, "error.404", 404);
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("error"))
                    .forward(request, response);
        }
    }
}
