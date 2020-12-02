package com.ilyabuglakov.xmltask.exception;

public class XMLFileContentException extends RuntimeException {
    public XMLFileContentException() {
        super();
    }

    public XMLFileContentException(String message) {
        super(message);
    }

    public XMLFileContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLFileContentException(Throwable cause) {
        super(cause);
    }
}
