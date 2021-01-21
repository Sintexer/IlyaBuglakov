package com.ilyabuglakov.raise.command.impl.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
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
public class TestPreviewPageCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer testId = null;
        try {
            String stringTestId = request.getParameter("testId");
            if(stringTestId!=null)
                testId = Integer.parseInt(stringTestId);
            else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        ResponseEntity responseEntity = new ResponseEntity();
        Optional<Test> test = Optional.empty();
        try(Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            TestReadService testReadService = new TestDatabaseReadService(transaction);
            test = testReadService.getTest(testId);
        } catch (DaoOperationException e) {
            log.error("Error while reading tests from db", e);
            response.sendError(500);
            return null;
        } catch (Exception e) {
            log.fatal("Error while closing transaction");
            response.sendError(500);
            return null;
        }

        if(test.isPresent() && test.get().getStatus() == TestStatus.CONFIRMED){
            log.info(test.get());
            responseEntity.setAttribute("test", test.get());
            responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("test.preview"));
        } else {
            response.sendError(404);
            return null;
        }
        return responseEntity;
    }
}
