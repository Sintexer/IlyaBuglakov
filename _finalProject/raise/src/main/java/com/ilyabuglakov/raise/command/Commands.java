package com.ilyabuglakov.raise.command;

import com.ilyabuglakov.raise.command.impl.index.IndexGetCommand;
import com.ilyabuglakov.raise.command.impl.registration.UserRegistrationCommand;
import com.ilyabuglakov.raise.command.impl.registration.UserRegistrationValidationCommand;
import com.ilyabuglakov.raise.command.impl.registration.UserUniquenessCheckCommand;
import lombok.Getter;

public enum Commands {

    INDEX(new IndexGetCommand()),
    USER_REG_VALIDATION(new UserRegistrationValidationCommand()),
    USER_REG_UNIQUENESS(new UserUniquenessCheckCommand()),
    USER_REG(new UserRegistrationCommand());


    @Getter
    private Command command;

    Commands(Command command) {
        this.command = command;
    }
}
