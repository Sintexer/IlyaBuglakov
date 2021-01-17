package com.ilyabuglakov.raise.command.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.service.domain.test.TestDatabaseReadService;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestReadService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
public class TestCatalogPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {
        int page = Integer.parseInt(request.getParameter("pageNumber"));
        int itemsPerPage = 4;

        Transaction transaction = new DatabaseTransactionFactory().createTransaction();
        TestReadService testReadService = new TestDatabaseReadService(transaction);
        List<Test> tests= null;
        try {
            tests = testReadService.getTestsInfo(page, itemsPerPage);
        } catch (DaoOperationException e) {
            log.error("Error while reading tests from db", e);
        }
        log.info(tests);
    }
}
