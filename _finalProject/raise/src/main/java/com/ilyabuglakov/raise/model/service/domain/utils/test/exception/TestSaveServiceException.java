package com.ilyabuglakov.raise.model.service.domain.utils.test.exception;

public class TestSaveServiceException extends Exception {
    public TestSaveServiceException() {
        super();
    }

    public TestSaveServiceException(String message) {
        super(message);
    }

    public TestSaveServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestSaveServiceException(Throwable cause) {
        super(cause);
    }
}
