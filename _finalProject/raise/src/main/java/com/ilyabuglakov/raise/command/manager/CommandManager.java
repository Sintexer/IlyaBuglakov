package com.ilyabuglakov.raise.command.manager;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.model.response.ResponseEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CommandManager {
    ResponseEntity execute(Command command, HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServletException, CommandException, IOException;

    void close();
}
