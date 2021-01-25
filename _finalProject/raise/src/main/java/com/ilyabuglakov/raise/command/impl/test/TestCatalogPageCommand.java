package com.ilyabuglakov.raise.command.impl.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.dto.PageInfoDto;
import com.ilyabuglakov.raise.model.dto.TestInfo;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.TestService;
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
public class TestCatalogPageCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException, PersistentException {

        ResponseEntity responseEntity = new ResponseEntity();

        TestService testService = (TestService) serviceFactory.createService(ServiceType.TEST);
        PageInfoDto pageInfoDto = CatalogService.getPageInfo(
                request.getParameter("pageNumber"),
                testService.getTestAmountByStatus(TestStatus.CONFIRMED),
                Integer.parseInt(ApplicationProperties.getProperty("catalog.page.items")));
        if (pageInfoDto.isIllegal()) {
            log.error(() -> "Illegal page" + pageInfoDto);
            response.sendError(404);
            return null;
        }

        List<TestInfo> testInfos = testService.getTestInfosByStatusAndPage(
                TestStatus.CONFIRMED,
                pageInfoDto.getItemsPerPage(),
                pageInfoDto.getCurrentPageIndex());

        log.info(testInfos);
        log.debug(() -> pageInfoDto);
        responseEntity.setAttribute("tests", testInfos);
        responseEntity.setAttribute("currentPage", pageInfoDto.getCurrentPage());
        responseEntity.setAttribute("maxPage", pageInfoDto.getMaxPage());
        responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("test.catalog"));
        return responseEntity;
    }
}
