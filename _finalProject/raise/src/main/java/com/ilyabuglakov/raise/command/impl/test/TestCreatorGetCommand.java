package com.ilyabuglakov.raise.command.impl.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.TestCategory;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.auth.AuthServiceFactory;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.TestCategoryService;
import com.ilyabuglakov.raise.model.service.domain.UserAccessValidationService;
import com.ilyabuglakov.raise.model.service.domain.test.TestDatabaseReadService;
import com.ilyabuglakov.raise.model.service.domain.test.exception.TestSaveServiceLimitException;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestReadService;
import com.ilyabuglakov.raise.model.service.domain.user.UserTransactionSearch;
import com.ilyabuglakov.raise.model.service.domain.user.interfaces.UserSearchService;
import com.ilyabuglakov.raise.model.service.property.ApplicationProperties;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import org.apache.shiro.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TestCreatorGetCommand extends Command {

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException, PersistentException {
        UserAccessValidationService accessValidationService =
                (UserAccessValidationService) serviceFactory.createService(ServiceType.USER_ACCESS_VALIDATION);
        ResponseEntity responseEntity =
                accessValidationService.isAllowedToCreateTest(AuthServiceFactory.getAuthService().getEmail());
        if(responseEntity.isErrorOccurred()){
            responseEntity.setAttribute("testLimitReached", true);
            responseEntity.setAttribute("testWasntCreated", true);
            responseEntity.setAttribute("testWasntPosted", true);
            responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("test.creator.save.failure"));
            return responseEntity;
        }
        responseEntity.setAttribute("characteristics", Characteristic.values());
        TestCategoryService testCategoryService = (TestCategoryService)serviceFactory.createService(ServiceType.TEST_CATEGORY);
        responseEntity.setAttribute("categories", testCategoryService.getCategoryMap());
        responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("test.creator"));

        return responseEntity;
    }

}
