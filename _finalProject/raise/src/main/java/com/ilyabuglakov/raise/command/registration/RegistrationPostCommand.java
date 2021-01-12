package com.ilyabuglakov.raise.command.registration;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.CommandName;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.command.exception.UserRegistrationException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.TransactionFactory;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
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
public class RegistrationPostCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("posted");

        try(Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            request.setAttribute("transaction", transaction);

            CommandName.USER_REG_VALIDATION.getCommand().execute(request, response);
            CommandName.USER_REG_UNIQUENESS.getCommand().execute(request, response);
            CommandName.USER_REG.getCommand().execute(request, response);

            transaction.commit();

            response.sendRedirect(PropertiesStorage.getInstance().getLinks().getProperty("login"));

        } catch (CommandException e) {
            request.setAttribute("registrationFailed", true);
            request.getRequestDispatcher(
                    PropertiesStorage.getInstance()
                            .getPages()
                            .getProperty("registration"))
                    .forward(request, response);
        } catch (Exception e) {
            log.fatal("Error while closing transaction");
        }
    }
}
