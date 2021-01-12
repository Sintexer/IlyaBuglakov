package com.ilyabuglakov.raise.command.registration;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.UserEmailUniquenessException;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.database.UserDatabaseDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.exception.TransactionException;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.DaoType;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Checks if user with email from request parameters exist in datasource or not.
 * If exist will throw UserEmailUniqueness exception and set userEmailAlreadyExist 'true'.
 *
 * Will try to rollback transaction if provided email already exists or if DaoOperationException happens.
 */
@Log4j2
public class UserUniquenessCheckCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UserEmailUniquenessException {
        Transaction transaction = (Transaction) request.getAttribute("transaction");
        try {
            UserDatabaseDao dao = (UserDatabaseDao)transaction.createDao(DaoType.USER);
            String email = request.getParameter("username");
            Optional<User> user = dao.findByEmail(email);
            if(user.isPresent()){
                request.setAttribute("userEmailAlreadyExist", true);
                transaction.rollback();
                throw new UserEmailUniquenessException("User with such email already exist");
            }
        } catch (DaoOperationException e) {
            try {
                transaction.rollback();
            } catch (TransactionException transactionException) {
                log.fatal("Can't rollback transaction", e);
            }
        } catch (TransactionException e) {
            log.fatal("Can't rollback transaction", e);
        }
    }
}
