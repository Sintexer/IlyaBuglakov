package com.ilyabuglakov.matrix.exception;

public class ReadMatrixException extends Exception {
    public ReadMatrixException() {
        super();
    }

    public ReadMatrixException(String message) {
        super(message);
    }

    public ReadMatrixException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadMatrixException(Throwable cause) {
        super(cause);
    }
}
