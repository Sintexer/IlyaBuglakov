package com.ilyabuglakov.raise.command.impl.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
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

public class TestCreatorGetCommand extends Command {

    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        ResponseEntity responseEntity = new ResponseEntity();
        try (Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            TestReadService testReadService = new TestDatabaseReadService(transaction);
            UserSearchService userSearchService = new UserTransactionSearch(transaction);

            Optional<User> user = userSearchService.findByEmail((String) SecurityUtils.getSubject().getPrincipal());
            Integer testAmount = testReadService.getNewTestAmount(user.orElseThrow(TestSaveServiceLimitException::new).getId());
            if(testAmount>= Integer.parseInt(ApplicationProperties.getProperty("user.max.new.tests")))
                throw new TestSaveServiceLimitException();
            responseEntity.setAttribute("characteristics", Characteristic.values());
            responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("test.creator"));
        } catch (TestSaveServiceLimitException e){
            responseEntity.setAttribute("testLimitReached", true);
            responseEntity.setAttribute("testWasntCreated", true);
            responseEntity.setAttribute("testWasntCPosted", true);
            responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("test.creator.save.failure"));
        } catch (PersistentException e) {
            response.sendError(500);
        }
        return responseEntity;
    }

}
