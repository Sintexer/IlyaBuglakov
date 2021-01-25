package com.ilyabuglakov.raise.command.exception;

public class UserEmailUniquenessException extends CommandException {
    public UserEmailUniquenessException() {
        super();
    }

    public UserEmailUniquenessException(String message) {
        super(message);
    }

    public UserEmailUniquenessException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailUniquenessException(Throwable cause) {
        super(cause);
    }
}
