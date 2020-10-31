package com.ilyabuglakov.task0201books.exception;

public class DaoWrongTypeException extends Exception {
    public DaoWrongTypeException() {
        super();
    }

    public DaoWrongTypeException(String message) {
        super(message);
    }

    public DaoWrongTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoWrongTypeException(Throwable cause) {
        super(cause);
    }
}
