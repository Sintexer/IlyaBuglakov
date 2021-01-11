package com.ilyabuglakov.raise.command;

import com.ilyabuglakov.raise.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException;
}
