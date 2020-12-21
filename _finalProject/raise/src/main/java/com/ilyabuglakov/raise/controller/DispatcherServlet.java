package com.ilyabuglakov.raise.controller;

import com.ilyabuglakov.raise.config.ApplicationConfig;
import com.ilyabuglakov.raise.config.exception.PoolConfigurationException;
import com.ilyabuglakov.raise.model.command.CommandName;
import com.ilyabuglakov.raise.service.property.PropertyParser;
import com.ilyabuglakov.raise.service.property.exception.PropertyFileException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("")
public class DispatcherServlet extends HttpServlet {

    private PropertyParser linksProperties;

    @Override
    public void init() throws ServletException {
        try {
            ApplicationConfig.initConnectionPool();
            linksProperties = new PropertyParser("links.properties");
            return;
        } catch (PoolConfigurationException e) {
            log.fatal("Can't init ConnectionPool through application.properties: " + e.getMessage(), e);
        } catch (PropertyFileException e) {
            log.fatal("Can't find or parse links.properties", e);
        } finally {
            destroy();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandAttribute = req.getParameter("command");
        if(commandAttribute!=null){

        }
        CommandName.INDEX.getCommand().execute(req, resp, getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
