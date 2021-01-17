package com.ilyabuglakov.raise.command.validation;

import javax.servlet.http.HttpServletRequest;

public interface ValidationCommand<T> {
    boolean execute(T object, HttpServletRequest request);
}
