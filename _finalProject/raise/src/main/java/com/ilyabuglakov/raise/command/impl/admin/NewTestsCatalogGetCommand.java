package com.ilyabuglakov.raise.command.impl.admin;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.model.dto.TestInfo;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.test.TestDatabaseReadService;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestReadService;
import com.ilyabuglakov.raise.model.service.property.ApplicationProperties;
import com.ilyabuglakov.raise.model.service.test.CatalogService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
public class NewTestsCatalogGetCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        ResponseEntity responseEntity = new ResponseEntity();

        String pageNumber = request.getParameter("pageNumber");
        int page = CatalogService.getPageNumber(pageNumber);

        int itemsPerPage = Integer.parseInt(ApplicationProperties.getProperty("catalog.page.items"));
        int maxPage = 0;

        List<TestInfo> testInfos;
        int testAmount = 0;
        try (Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            TestReadService testReadService = new TestDatabaseReadService(transaction);
            testAmount = testReadService.getNewTestAmount();

            maxPage = CatalogService.getMaxPage(testAmount, itemsPerPage);
            if(page > maxPage || page < 1){
                response.sendError(404);
                return null;
            }

            testInfos = testReadService.getNewTestInfos(page - 1, itemsPerPage);
        } catch (PersistentException e) {
            response.sendError(500);
            return null;
        }

        page = Math.min(page, maxPage);
        log.info(testInfos);
        responseEntity.setAttribute("tests", testInfos);
        responseEntity.setAttribute("currentPage", page);
        responseEntity.setAttribute("maxPage", maxPage);
        responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("admin.test.catalog"));
        return responseEntity;
    }
}
