package com.ilyabuglakov.raise.model.command.impl;

import com.ilyabuglakov.raise.model.Forward;
import com.ilyabuglakov.raise.model.command.Command;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class IndexCommand implements Command {
    @Override
    public void executeGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("Entered index command");
        Forward forward = new Forward(PropertiesStorage.getInstance().getPages().getProperty("index"));
        //servletContext.getRequestDispatcher(forward.getForward()).forward(request, response);
    }

    @Override
    public void executePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.warn("Index post command");
    }
}
