package com.ilyabuglakov.raise.model.command.impl;

import com.ilyabuglakov.raise.model.command.Command;
import com.ilyabuglakov.raise.model.command.CommandName;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToIndexCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        servletContext.getRequestDispatcher(CommandName.linksProperties.getProperty("index")).forward(request, response);
    }
}
