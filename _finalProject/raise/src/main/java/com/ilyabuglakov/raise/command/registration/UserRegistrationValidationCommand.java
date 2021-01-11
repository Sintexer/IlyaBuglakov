package com.ilyabuglakov.raise.command.registration;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.UserValidationException;
import com.ilyabuglakov.raise.model.service.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * checks user registration form parameters for validity.
 * <p>
 * Will throw UserValidationException if some fields are invalid.
 */
public class UserRegistrationValidationCommand implements Command {


    /**
     * In case of invalid fields will put error messages to request attributes,
     * such as invalidEmail, invalidName, invalidSurname, invalidPassword.
     * Also will save previous user fields input in attributes emailPrevVal, namePrevVal, surnamePrevVal
     *
     * @param request Servlet request
     * @param response Servlet response
     * @throws UserValidationException if some fields are invalid
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws UserValidationException {
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
            request.setAttribute("surnamePrevVal", surname);
            throw new UserValidationException("Some of form fields contain invalid value");
        }
    }
}
