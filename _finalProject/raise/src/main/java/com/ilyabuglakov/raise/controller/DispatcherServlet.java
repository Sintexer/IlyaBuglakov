package com.ilyabuglakov.raise.controller;

import com.ilyabuglakov.raise.config.ApplicationConfig;
import com.ilyabuglakov.raise.config.exception.PoolConfigurationException;
import com.ilyabuglakov.raise.model.Forward;
import com.ilyabuglakov.raise.model.command.Command;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("/controller")
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            ApplicationConfig.initConnectionPool();
        } catch (PoolConfigurationException e) {
            log.fatal("Can't init ConnectionPool through application.properties: " + e.getMessage(), e);
            destroy();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object commandAttribute = req.getAttribute("command");
        Command command;
        String url;
        if (commandAttribute != null) {
            command = (Command) commandAttribute;
            log.info("Command: " + command.getClass());
            Forward forward = command.execute(req, resp, getServletContext());
            log.warn(forward.getForward());
            url = forward.getForward();
        } else {
            url = req.getRequestURI();
        }
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
