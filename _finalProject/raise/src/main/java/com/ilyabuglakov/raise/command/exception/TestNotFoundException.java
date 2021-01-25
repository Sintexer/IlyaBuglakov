package com.ilyabuglakov.raise.command.exception;

public class TestNotFoundException extends RuntimeException {
    public TestNotFoundException() {
        super();
    }

    public TestNotFoundException(String message) {
        super(message);
    }

    public TestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestNotFoundException(Throwable cause) {
        super(cause);
    }
}
