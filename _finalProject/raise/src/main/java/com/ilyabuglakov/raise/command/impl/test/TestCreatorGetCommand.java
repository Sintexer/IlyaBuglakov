package com.ilyabuglakov.raise.command.impl.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import com.ilyabuglakov.raise.model.service.RequestService;
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
import java.util.Optional;

public class TestCreatorGetCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {

        try (Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            TestReadService testReadService = new TestDatabaseReadService(transaction);
            UserSearchService userSearchService = new UserTransactionSearch(transaction);

            Optional<User> user = userSearchService.findByEmail((String) SecurityUtils.getSubject().getPrincipal());
            Integer testAmount = testReadService.getNewTestAmount(user.orElseThrow(TestSaveServiceLimitException::new).getId());
            if(testAmount>= Integer.parseInt(ApplicationProperties.getProperty("user.max.new.tests")))
                throw new TestSaveServiceLimitException();
            request.setAttribute("characteristics", Characteristic.values());
            request.getRequestDispatcher(PropertiesStorage.getInstance()
                    .getPages()
                    .getProperty("test.creator"))
                    .forward(request, response);
        } catch (TestSaveServiceLimitException e){
            request.setAttribute("testLimitReached", true);
            forwardToFailure(request, response);
        } catch (Exception e) {
            RequestService.getInstance().setRequestErrorAttributes(request, "error.db", 500);
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("error"))
                    .forward(request, response);
        }


    }

    private void forwardToFailure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("testWasntCreated", true);
        request.getRequestDispatcher(PropertiesStorage.getInstance()
                .getPages()
                .getProperty("test.creator.save.failure"))
                .forward(request, response);
    }

}
