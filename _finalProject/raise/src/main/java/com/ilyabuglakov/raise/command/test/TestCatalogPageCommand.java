package com.ilyabuglakov.raise.command.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.model.dto.TestInfo;
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

@Log4j2
public class TestCatalogPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {
        String pageNumber = request.getParameter("pageNumber");
        int page = TestCatalogService.getPageNumber(pageNumber);

        int itemsPerPage = 4;

        List<TestInfo> testInfos= null;
        int testAmount = 0;
        try(Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            TestReadService testReadService = new TestDatabaseReadService(transaction);
            testInfos = testReadService.getTestInfos(page-1, itemsPerPage);
             testAmount = testReadService.getTestAmount();
        } catch (DaoOperationException e) {
            log.error("Error while reading tests from db", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (Exception e) {
            log.fatal("Error while closing transaction");
        }


        int maxPage =TestCatalogService.getMaxPage(testAmount, itemsPerPage);
        page = Math.min(page, maxPage);
        log.info(testInfos);
        request.setAttribute("testInfos", testInfos);
        request.setAttribute("currentPage", page);
        request.setAttribute("maxPage", maxPage);
        request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("test.catalog"))
                .forward(request, response);
    }
}
