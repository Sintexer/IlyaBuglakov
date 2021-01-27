package com.ilyabuglakov.raise.model.service.domain.utils.user.exception;

public class UserParametersServiceException extends Exception {
    public UserParametersServiceException() {
        super();
    }

    public UserParametersServiceException(String message) {
        super(message);
    }

    public UserParametersServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserParametersServiceException(Throwable cause) {
        super(cause);
    }
}
