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
import com.ilyabuglakov.raise.model.response.ResponseEntity;
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
public class TestCreatorSaveCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {

        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            response.sendError(401);
            return null;
        }

        ResponseEntity responseEntity = new ResponseEntity();

        String body = request.getParameter("testJson");
        log.info(body);

        Gson gson = new Gson();
        Test test = gson.fromJson(body, Test.class);

        try (Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {

            if (subject.isPermitted("confirm:test")) {
                test.setStatus(TestStatus.CONFIRMED);
            }
            boolean isValid = ValidationCommands.TEST_VALIDATION.getCommand().execute(test, request);
            if (!isValid) {
                forwardToFailure(responseEntity, request, response);
                return responseEntity;
            }

            TestSaveService testSaveService = new TestDatabaseSaveService(transaction);
            UserSearchService userSearchService = new UserTransactionSearch(transaction);

            User user = userSearchService.findByEmail((String) subject.getPrincipal()).orElse(null);
            test.setAuthor(user);
            testSaveService.save(test);
            transaction.commit();
            responseEntity.setAttribute("testName", test.getTestName());
            responseEntity.setLink(PropertiesStorage.getInstance()
                    .getPages()
                    .getProperty("test.creator.save.success"));
        } catch (TestSaveServiceLimitException e) {
            request.setAttribute("testLimitReached", true);
            request.setAttribute("testWasntCreated", true);
            request.setAttribute("testWasntPosted", true);
            forwardToFailure(responseEntity, request, response);
        } catch (TestSaveServiceException e) {
            response.sendError(500);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            response.sendError(404);
            return null;
        }
        return responseEntity;
    }

    private void forwardToFailure(ResponseEntity responseEntity,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        responseEntity.setAttribute("testWasntCreated", true);
        responseEntity.setLink(PropertiesStorage.getInstance()
                .getPages()
                .getProperty("test.creator.save.failure"));
    }

}
