package com.ilyabuglakov.raise.model.command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext);
}
