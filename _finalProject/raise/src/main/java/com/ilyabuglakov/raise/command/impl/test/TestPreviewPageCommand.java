package com.ilyabuglakov.raise.command.impl.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.TestComment;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.dto.PageInfoDto;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.TestCommentService;
import com.ilyabuglakov.raise.model.service.domain.TestService;
import com.ilyabuglakov.raise.model.service.property.ApplicationProperties;
import com.ilyabuglakov.raise.model.service.servlet.RequestService;
import com.ilyabuglakov.raise.model.service.test.CatalogService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Log4j2
public class TestPreviewPageCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, PersistentException {
        Optional<Integer> testId = RequestService.getInstance().getIntParameter(request, "testId");
        if (!testId.isPresent()) {
            log.debug(() -> "testId is not present");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        TestCommentService testCommentService = (TestCommentService) serviceFactory.createService(ServiceType.TEST_COMMENT);
        ResponseEntity responseEntity = new ResponseEntity();

        PageInfoDto pageInfoDto = CatalogService.getPageInfo(
                request.getParameter("pageNumber"),
                testCommentService.getCommentsAmount(testId.get()),
                Integer.parseInt(ApplicationProperties.getProperty("comments.page.items")));
        if(pageInfoDto.isIllegal()){
            log.debug(() ->"Illegal page " + pageInfoDto);
            response.sendError(404);
            return null;
        }

        List<TestComment> comments = testCommentService.getComments(
                testId.get(),
                pageInfoDto.getCurrentPageIndex(),
                pageInfoDto.getItemsPerPage());

        TestService testService = (TestService) serviceFactory.createService(ServiceType.TEST);
        Test test = testService.getTest(testId.get()).orElseThrow(PersistentException::new);

        responseEntity.setAttribute("comments", comments);
        responseEntity.setAttribute("test", test);
        responseEntity.setAttribute("currentPage", pageInfoDto.getCurrentPage());
        responseEntity.setAttribute("maxPage", pageInfoDto.getMaxPage());

        if (test.getStatus() != TestStatus.CONFIRMED
                && !SecurityUtils.getSubject().isPermitted("test:confirm")) {
            response.sendError(404);
            return null;
        }
        responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("test.preview"));
        return responseEntity;
    }
}
