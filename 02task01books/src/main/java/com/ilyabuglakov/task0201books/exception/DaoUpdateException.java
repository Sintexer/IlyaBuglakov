package com.ilyabuglakov.task0201books.exception;

public class DaoUpdateException extends Exception {
    public DaoUpdateException() {
        super();
    }

    public DaoUpdateException(String message) {
        super(message);
    }

    public DaoUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoUpdateException(Throwable cause) {
        super(cause);
    }
}
