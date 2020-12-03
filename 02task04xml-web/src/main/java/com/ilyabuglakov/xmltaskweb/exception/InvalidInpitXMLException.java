package com.ilyabuglakov.xmltaskweb.exception;

public class InvalidInpitXMLException extends Exception {
    public InvalidInpitXMLException() {
        super();
    }

    public InvalidInpitXMLException(String message) {
        super(message);
    }

    public InvalidInpitXMLException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInpitXMLException(Throwable cause) {
        super(cause);
    }
}
