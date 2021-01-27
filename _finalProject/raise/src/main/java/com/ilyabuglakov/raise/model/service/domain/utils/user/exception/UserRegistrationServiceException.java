package com.ilyabuglakov.raise.model.service.domain.utils.user.exception;

public class UserRegistrationServiceException extends Exception {
    public UserRegistrationServiceException() {
        super();
    }

    public UserRegistrationServiceException(String message) {
        super(message);
    }

    public UserRegistrationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRegistrationServiceException(Throwable cause) {
        super(cause);
    }
}
