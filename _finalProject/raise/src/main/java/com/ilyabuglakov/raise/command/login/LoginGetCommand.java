package com.ilyabuglakov.raise.command.login;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.model.FormConstants;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class LoginGetCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        log.info("Entered login command");

        request.setAttribute("emailLength", FormConstants.EMAIL_LENGTH.getValue());
        request.setAttribute("passwordMax", FormConstants.PASSWORD_MAX.getValue());

        request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("login"))
                .forward(request, response);
    }

}
