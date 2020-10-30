package com.ilyabuglakov.task0201books.exception;

public class DaoAddException extends Exception {
    public DaoAddException() {
        super();
    }

    public DaoAddException(String message) {
        super(message);
    }

    public DaoAddException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoAddException(Throwable cause) {
        super(cause);
    }

    protected DaoAddException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
