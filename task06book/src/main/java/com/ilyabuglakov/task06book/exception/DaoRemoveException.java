package com.ilyabuglakov.task06book.exception;

public class DaoRemoveException extends Exception {
    public DaoRemoveException() {
    }

    public DaoRemoveException(String message) {
        super(message);
    }

    public DaoRemoveException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoRemoveException(Throwable cause) {
        super(cause);
    }

    public DaoRemoveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
