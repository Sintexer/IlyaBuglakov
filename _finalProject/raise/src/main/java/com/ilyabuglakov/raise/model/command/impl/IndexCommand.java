package com.ilyabuglakov.raise.model.command.impl;

import com.ilyabuglakov.raise.model.command.Command;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class IndexCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("Entered index command");
        request.getRequestDispatcher(PropertiesStorage.getInstance()
                .getPages()
                .getProperty("index"))
                .forward(request, response);
    }
}
