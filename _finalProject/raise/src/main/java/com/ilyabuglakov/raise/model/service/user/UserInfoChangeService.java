package com.ilyabuglakov.raise.model.service.user;

import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.service.validator.UserCredentialsValidator;
import com.ilyabuglakov.raise.model.service.validator.UserValidator;

public class UserInfoChangeService {

    public boolean changeName(User user, String name) {
        UserValidator userValidator = new UserValidator();
        if (userValidator.isValidName(name)) {
            user.setName(name);
            return true;
        }
        return false;
    }

    public boolean changeSurname(User user, String surname) {
        UserValidator userValidator = new UserValidator();
        if (userValidator.isValidSurname(surname)) {
            user.setSurname(surname);
            return true;
        }
        return false;
    }

    public boolean changePassword(User user, String newPassword, String newPasswordRepeat) {
        UserCredentialsValidator userCredentialsValidator = new UserCredentialsValidator();
        if (userCredentialsValidator.isValidNewPassword(newPassword, newPasswordRepeat)) {
            //todo hashing
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }

}
