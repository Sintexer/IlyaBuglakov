package com.ilyabuglakov.raise.model.service.servlet.exception;

public class IllegalRequestParameterException extends Exception {
    public IllegalRequestParameterException() {
        super();
    }

    public IllegalRequestParameterException(String message) {
        super(message);
    }

    public IllegalRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRequestParameterException(Throwable cause) {
        super(cause);
    }
}
