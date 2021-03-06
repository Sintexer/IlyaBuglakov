package com.ilyabuglakov.stringmanipulator.exception;

/**
 * This Exception will be thrown away, when file, that is used for input, is empty
 */
public class ReadException extends Exception {
    public ReadException() {
    }

    public ReadException(String message) {
        super(message);
    }

    public ReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadException(Throwable cause) {
        super(cause);
    }

    public ReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
