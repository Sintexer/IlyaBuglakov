package com.ilyabuglakov.raise.command.test;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class TestSaveCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String body = request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
        log.info(body);
        response.setStatus(200);
    }
}
