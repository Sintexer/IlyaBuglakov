package com.ilyabuglakov.raise.command.impl.registration;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.Commands;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Any command, tht use transaction from request attributes should rollback transaction
 * before throwing an exception.
 */
@Log4j2
public class RegistrationPostCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("posted");
        ResponseEntity responseEntity = new ResponseEntity();
        try(Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            request.setAttribute("transaction", transaction);

            Commands.USER_REG_VALIDATION.getCommand().execute(request, response);
            Commands.USER_REG_UNIQUENESS.getCommand().execute(request, response);
            Commands.USER_REG.getCommand().execute(request, response);

            transaction.commit();

            responseEntity.setLink(PropertiesStorage.getInstance().getLinks().getProperty("login"));
            responseEntity.setRedirect(true);

        } catch (CommandException e) {
            responseEntity.getAttributes().put("registrationFailed", true);
            responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("registration"));
        } catch (Exception e) {
            log.fatal("Error while closing transaction");
            responseEntity.getAttributes().put("registrationFailed", true);
            responseEntity.getAttributes().put("errorMessage", "error.db");
            responseEntity.getAttributes().put("errorCode", "500");
            responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("error"));
        }
        return responseEntity;
    }
}