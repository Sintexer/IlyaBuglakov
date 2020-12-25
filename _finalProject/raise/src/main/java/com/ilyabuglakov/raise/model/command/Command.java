package com.ilyabuglakov.raise.model.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    default void executeGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher(request.getRequestURI()).forward(request, response);
    }

    default void executePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
