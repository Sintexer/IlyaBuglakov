package com.ilyabuglakov.raise.command.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.model.dto.TestInfo;
import com.ilyabuglakov.raise.model.service.RequestService;
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
        int maxPage = 0;

        List<TestInfo> testInfos = null;
        int testAmount = 0;
        try (Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            TestReadService testReadService = new TestDatabaseReadService(transaction);
            testAmount = testReadService.getTestAmount();

            maxPage = 1 + TestCatalogService.getMaxPage(testAmount, itemsPerPage);
            if(page > maxPage || page < 1){
                RequestService.getInstance().setRequestErrorAttributes(request, "error.404", 404);
                request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("error"))
                        .forward(request, response);
                return;
            }

            testInfos = testReadService.getTestInfos(page - 1, itemsPerPage);
        } catch (DaoOperationException e) {
            log.error("Error while reading tests from db", e);
            RequestService.getInstance().setRequestErrorAttributes(request, "error.db", 500);
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("error"))
                    .forward(request, response);
            return;
        } catch (Exception e) {
            log.fatal("Error while closing transaction");
        }

        page = Math.min(page, maxPage);
        log.info(testInfos);
        request.setAttribute("testInfos", testInfos);
        request.setAttribute("currentPage", page);
        request.setAttribute("maxPage", maxPage);
        request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("test.catalog"))
                .forward(request, response);
    }
}
