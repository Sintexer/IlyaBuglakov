package com.ilyabuglakov.raise.command.impl.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.TestCategory;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.dto.PageInfoDto;
import com.ilyabuglakov.raise.model.dto.TestInfo;
import com.ilyabuglakov.raise.model.dto.TestSearchParametersDto;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.TestCategoryService;
import com.ilyabuglakov.raise.model.service.domain.TestService;
import com.ilyabuglakov.raise.model.service.property.ApplicationProperties;
import com.ilyabuglakov.raise.model.service.servlet.RequestService;
import com.ilyabuglakov.raise.model.service.test.CatalogService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Log4j2
public class TestCatalogPageCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException, PersistentException {

        ResponseEntity responseEntity = new ResponseEntity();

        String pageNumber = request.getParameter("pageNumber");

        TestService testService = (TestService) serviceFactory.createService(ServiceType.TEST);
        TestCategoryService testCategoryService = (TestCategoryService) serviceFactory.createService(ServiceType.TEST_CATEGORY);
        PageInfoDto pageInfoDto = CatalogService.getPageInfo(
                pageNumber,
                testService.getTestAmountByStatus(TestStatus.CONFIRMED),
                Integer.parseInt(ApplicationProperties.getProperty("catalog.page.items")));
        if (pageInfoDto.isIllegal()) {
            log.error(() -> "Illegal page" + pageInfoDto);
            response.sendError(404);
            return null;
        }
        Optional<Integer> categoryId = RequestService.getInstance().getIntParameter(request, "category");
        if(!categoryId.isPresent())
            categoryId = RequestService.getInstance().getIntParameter(request, "stashedCategory");

        TestSearchParametersDto searchParametersDto = TestSearchParametersDto.builder()
                .testName(request.getParameter("testName"))
                .page(pageInfoDto.getCurrentPageIndex())
                .limit(pageInfoDto.getItemsPerPage())
                .status(TestStatus.CONFIRMED)
                .build();
        categoryId.ifPresent(integer -> searchParametersDto.setCategory(TestCategory.builder().id(integer).build()));

        List<TestInfo> testInfos;
        if(searchParametersDto.hasSearchParameters()){
            testInfos = testService.findBySearchParameters(searchParametersDto);
            searchParametersDto.setCategory(testCategoryService.getCategory(searchParametersDto.getCategory().getId()).orElse(null));
        }else{
            testInfos = testService.getTestInfosByStatusAndPage(
                TestStatus.CONFIRMED,
                pageInfoDto.getItemsPerPage(),
                pageInfoDto.getCurrentPageIndex());
        }

        log.info(testInfos);
        log.debug(() -> pageInfoDto);
        responseEntity.setAttribute("stashedCategory", searchParametersDto.getCategory());
        responseEntity.setAttribute("stashedTestName", searchParametersDto.getTestName());
        responseEntity.setAttribute("tests", testInfos);
        responseEntity.setAttribute("categories", testCategoryService.getCategoryMap());
        responseEntity.setAttribute("currentPage", pageInfoDto.getCurrentPage());
        responseEntity.setAttribute("maxPage", pageInfoDto.getMaxPage());
        responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("test.catalog"));
        return responseEntity;
    }
}
