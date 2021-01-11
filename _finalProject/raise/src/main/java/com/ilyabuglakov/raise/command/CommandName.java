package com.ilyabuglakov.raise.command;

import com.ilyabuglakov.raise.command.impl.IndexGetCommand;
import com.ilyabuglakov.raise.command.registration.UserRegistrationCommand;
import com.ilyabuglakov.raise.command.registration.UserRegistrationValidationCommand;
import com.ilyabuglakov.raise.command.registration.UserUniquenessCheckCommand;
import lombok.Getter;

public enum CommandName {

    INDEX(new IndexGetCommand()),
    USER_REG_VALIDATION(new UserRegistrationValidationCommand()),
    USER_REG_UNIQUENESS(new UserUniquenessCheckCommand()),
    USER_REG(new UserRegistrationCommand());

    @Getter
    private Command command;

    CommandName(Command command) {
        this.command = command;
    }
}
