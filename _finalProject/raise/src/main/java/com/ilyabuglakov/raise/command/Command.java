package com.ilyabuglakov.raise.command;

import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.factory.ServiceFactory;
import lombok.Setter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {
    @Setter
    protected ServiceFactory serviceFactory;

    public abstract ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException;
}
