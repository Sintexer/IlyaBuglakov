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
public class RegistrationCommand implements Command {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        log.info("Entered registration command");

        return new Forward(PropertiesStorage.getInstance().getPages().getProperty("registration"));
    }
}
