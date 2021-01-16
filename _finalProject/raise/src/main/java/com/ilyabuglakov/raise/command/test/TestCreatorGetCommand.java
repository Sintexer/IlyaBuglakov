package com.ilyabuglakov.raise.command.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import com.ilyabuglakov.raise.storage.PropertiesStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestCreatorGetCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        request.setAttribute("characteristics", Characteristic.values());
        request.getRequestDispatcher(PropertiesStorage.getInstance()
                .getPages()
                .getProperty("test.creator"))
                .forward(request, response);
    }

}
