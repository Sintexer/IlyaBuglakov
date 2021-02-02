package com.ilyabuglakov.raise.model.service.validator;

import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.Patterns;
import org.apache.shiro.crypto.hash.Sha256Hash;

public class UserCredentialsValidator {

    public boolean isCorrectOldPassword(User user, String oldPassword) {
        //todo hashing
        return user.getPassword().equals(new Sha256Hash(oldPassword).toHex());

    }

    public boolean isValidNewPassword(String newPassword, String newPasswordRepeat) {
        if (!newPassword.equals(newPasswordRepeat))
            return false;
        return newPassword.matches(Patterns.PASSWORD.getPattern());
    }

}
