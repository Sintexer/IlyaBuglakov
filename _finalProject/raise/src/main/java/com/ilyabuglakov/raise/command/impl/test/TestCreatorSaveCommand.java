package com.ilyabuglakov.raise.command.impl.test;

import com.google.gson.Gson;
import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.ValidationCommands;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.service.RequestService;
import com.ilyabuglakov.raise.model.service.domain.test.TestDatabaseSaveService;
import com.ilyabuglakov.raise.model.service.domain.test.exception.TestSaveServiceException;
import com.ilyabuglakov.raise.model.service.domain.test.exception.TestSaveServiceLimitException;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestSaveService;
import com.ilyabuglakov.raise.model.service.domain.user.UserTransactionSearch;
import com.ilyabuglakov.raise.model.service.domain.user.interfaces.UserSearchService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class TestCreatorSaveCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {

        Subject subject = SecurityUtils.getSubject();

        if(subject == null){
            response.setStatus(401);
            return;
        }

        String body = request.getParameter("testJson");
        log.info(body);

        Gson gson = new Gson();
        Test test = gson.fromJson(body, Test.class);

        try (Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {

            if(subject.isPermitted("confirm:test")){
                test.setStatus(TestStatus.CONFIRMED);
            }

            boolean isValid = ValidationCommands.TEST_VALIDATION.getCommand().execute(test, request);
            if (!isValid) {
                forwardToFailure(request, response);
                return;
            }

            TestSaveService testSaveService = new TestDatabaseSaveService(transaction);
            UserSearchService userSearchService = new UserTransactionSearch(transaction);

            User user = userSearchService.findByEmail((String)subject.getPrincipal()).orElse(null);
            test.setAuthor(user);
            testSaveService.save(test);
            transaction.commit();
            request.setAttribute("testName", test.getTestName());
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("test.creator.save.success"))
                    .forward(request, response);
        } catch(TestSaveServiceLimitException e) {
            request.setAttribute("testLimitReached", true);
            forwardToFailure(request, response);
        } catch (TestSaveServiceException e) {
            RequestService.getInstance().setRequestErrorAttributes(request, "error.test.db.save", 500);
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("error"))
                    .forward(request, response);
        } catch (Exception e) {
            RequestService.getInstance().setRequestErrorAttributes(request, "error.404", 404);
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
