package com.ilyabuglakov.raise.command.registration;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.UserRegistrationException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.service.web.user.UserDatabaseRegistrationService;
import com.ilyabuglakov.raise.service.web.user.exception.UserRegistrationServiceException;
import com.ilyabuglakov.raise.service.web.user.interfaces.UserRegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws UserRegistrationException {

        String email = request.getParameter("username");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");

        User user = User.builder()
                .email(email.toLowerCase())
                .name(name)
                .surname(surname)
                .password(password)
                .build();

        Transaction transaction = (Transaction) request.getAttribute("transaction");

        UserRegistrationService registrationService = new UserDatabaseRegistrationService(transaction);
        try {
            registrationService.save(user);
        } catch (UserRegistrationServiceException e) {
            throw new UserRegistrationException("", e);
        }
    }

}
