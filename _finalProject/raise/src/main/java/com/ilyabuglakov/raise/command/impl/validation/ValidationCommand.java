package com.ilyabuglakov.raise.command.impl.validation;

import javax.servlet.http.HttpServletRequest;

public interface ValidationCommand<T> {
    boolean execute(T object, HttpServletRequest request);
}
