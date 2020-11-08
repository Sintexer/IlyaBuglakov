package com.ilyabuglakov.matrix.exception;

public class RowParseException extends Exception {
    public RowParseException() {
        super();
    }

    public RowParseException(String message) {
        super(message);
    }

    public RowParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public RowParseException(Throwable cause) {
        super(cause);
    }
}
