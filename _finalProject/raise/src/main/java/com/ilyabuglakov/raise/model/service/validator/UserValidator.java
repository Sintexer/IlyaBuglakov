package com.ilyabuglakov.raise.model.service.validator;

import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.FormConstants;

/**
 * UserValidator can validate email, name and surname by regex and check if password length is in bounds.
 * Can validate whole User object by separate fields
 */
public class UserValidator {

    private static final String INVALID_NAME_PATTERN = "([- ]){0,2}";
    private static final String VALID_EMAIL_PATTERN = ".+@.+\\..+";

    public boolean isValidEmail(String email) {
        return email.matches(VALID_EMAIL_PATTERN);
    }

    public boolean isValidName(String name) {
        return !name.matches(INVALID_NAME_PATTERN);
    }

    public boolean isValidSurname(String surname) {
        return !surname.matches(INVALID_NAME_PATTERN);
    }

    public boolean isValidPassword(String password) {
        return password.length() >= FormConstants.PASSWORD_MIN.getValue() &&
                password.length() < FormConstants.PASSWORD_MAX.getValue();
    }

    public boolean isValid(User user) {
        return isValidUserParameters(user.getEmail(), user.getName(), user.getSurname(), user.getPassword());
    }

    public boolean isValidUserParameters(String email, String name, String surname, String password) {
        return isValidEmail(email) &&
                isValidName(name) &&
                isValidSurname(surname) &&
                isValidPassword(password);
    }

}
