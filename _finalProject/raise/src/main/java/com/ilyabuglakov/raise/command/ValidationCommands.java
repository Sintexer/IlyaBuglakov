package com.ilyabuglakov.raise.command;

import com.ilyabuglakov.raise.command.impl.validation.TestValidationCommand;
import com.ilyabuglakov.raise.command.impl.validation.ValidationCommand;
import lombok.Getter;

@Getter
public enum ValidationCommands {
    TEST_VALIDATION(new TestValidationCommand());

    private ValidationCommand command;

    ValidationCommands(ValidationCommand command) {
        this.command = command;
    }
}
