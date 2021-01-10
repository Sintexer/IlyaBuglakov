package com.ilyabuglakov.raise.service.web.user.exception;

public class UserSearchException extends Exception{
    public UserSearchException() {
        super();
    }

    public UserSearchException(String message) {
        super(message);
    }

    public UserSearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserSearchException(Throwable cause) {
        super(cause);
    }
}
