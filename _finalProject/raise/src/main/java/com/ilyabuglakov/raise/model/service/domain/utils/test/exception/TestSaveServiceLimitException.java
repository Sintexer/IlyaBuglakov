package com.ilyabuglakov.raise.model.service.domain.utils.test.exception;

public class TestSaveServiceLimitException extends TestSaveServiceException {
    public TestSaveServiceLimitException() {
        super();
    }

    public TestSaveServiceLimitException(String message) {
        super(message);
    }

    public TestSaveServiceLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestSaveServiceLimitException(Throwable cause) {
        super(cause);
    }
}
