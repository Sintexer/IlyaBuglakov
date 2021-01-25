package com.ilyabuglakov.raise.model.service.validator;

import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.Patterns;

public class UserCredentialsValidator {

    public boolean isCorrectOldPassword(User user, String oldPassword) {
        //todo hashing
        return user.getPassword().equals(oldPassword);

    }

    public boolean isValidNewPassword(String newPassword, String newPasswordRepeat) {
        if (!newPassword.equals(newPasswordRepeat))
            return false;
        return newPassword.matches(Patterns.PASSWORD.getPattern());
    }

}
