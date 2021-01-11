package com.ilyabuglakov.raise.model.service.web.user.exception;

public class UserSearchServiceException extends Exception{
    public UserSearchServiceException() {
        super();
    }

    public UserSearchServiceException(String message) {
        super(message);
    }

    public UserSearchServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserSearchServiceException(Throwable cause) {
        super(cause);
    }
}
