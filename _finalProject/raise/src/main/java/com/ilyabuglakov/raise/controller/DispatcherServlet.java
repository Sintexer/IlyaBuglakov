package com.ilyabuglakov.raise.controller;

import com.ilyabuglakov.raise.config.ApplicationConfig;
import com.ilyabuglakov.raise.config.exception.PoolConfigurationException;
import com.ilyabuglakov.raise.dal.connection.pool.StandardConnectionPool;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try{
            ApplicationConfig.initConnectionPool();
        } catch (PoolConfigurationException e) {
            log.fatal("Can't init ConnectionPool through application.properties: " + e.getMessage(), e);
            destroy();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
