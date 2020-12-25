package com.ilyabuglakov.raise.controller;

import com.ilyabuglakov.raise.config.ApplicationConfig;
import com.ilyabuglakov.raise.config.exception.PoolConfigurationException;
import com.ilyabuglakov.raise.model.command.Command;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

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
        Optional<Command> command = extractCommand(req);
        if (command.isPresent()) {
            command.get().executeGet(req, resp);
        } else {
            getServletContext().getRequestDispatcher(req.getRequestURI()).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Command> command = extractCommand(req);
        log.info("Entered post");
        if (command.isPresent()) {
            command.get().executePost(req, resp);
        } else {
            getServletContext().getRequestDispatcher(req.getRequestURI()).forward(req, resp);
        }
    }

    private Optional<Command> extractCommand(HttpServletRequest request) {
        return Optional.ofNullable((Command) request.getAttribute("command"));
    }


}
