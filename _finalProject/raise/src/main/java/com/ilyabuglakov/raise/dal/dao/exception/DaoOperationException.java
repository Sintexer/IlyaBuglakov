package com.ilyabuglakov.raise.dal.dao.exception;

public class DaoOperationException extends Exception {
    public DaoOperationException() {
        super();
    }

    public DaoOperationException(String message) {
        super(message);
    }

    public DaoOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoOperationException(Throwable cause) {
        super(cause);
    }
}
