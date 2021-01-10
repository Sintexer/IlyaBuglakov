package com.ilyabuglakov.raise.command.impl;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.service.validator.UserValidator;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class RegistrationPostCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("posted");

        String email = request.getParameter("username");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");

        UserValidator validator = new UserValidator();

        boolean isValidEmail = validator.isValidEmail(email);
        boolean isValidName = validator.isValidName(name);
        boolean isValidSurname = validator.isValidSurname(surname);
        boolean isValidPassword = validator.isValidPassword(password);
        boolean isValid = isValidEmail && isValidName && isValidSurname && isValidPassword;

        if (!isValid) {
            request.setAttribute("invalidEmail", !isValidEmail);
            request.setAttribute("invalidName", !isValidName);
            request.setAttribute("invalidSurname", !isValidSurname);
            request.setAttribute("invalidPassword", !isValidPassword);

            request.setAttribute("emailPrevVal", email);
            request.setAttribute("namePrevVal", name);
            request.setAttribute("surnamePrevVal",surname);
            request.getRequestDispatcher(
                    PropertiesStorage.getInstance()
                            .getPages()
                            .getProperty("registration"))
                    .forward(request, response);
        }

        //TODO RegistrationService
    }
}
