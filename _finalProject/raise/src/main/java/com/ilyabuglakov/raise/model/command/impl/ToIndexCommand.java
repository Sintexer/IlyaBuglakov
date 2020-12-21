package com.ilyabuglakov.raise.model.command.impl;

import com.ilyabuglakov.raise.model.command.Command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToIndexCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
        try {
            servletContext.getRequestDispatcher("/template/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
