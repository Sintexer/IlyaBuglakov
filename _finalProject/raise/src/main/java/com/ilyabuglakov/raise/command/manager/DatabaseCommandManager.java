package com.ilyabuglakov.raise.command.manager;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DatabaseCommandManager implements CommandManager{

    private final ServiceFactory serviceFactory;

    public DatabaseCommandManager(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public ResponseEntity execute(Command command, HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ServletException, CommandException, IOException {
        command.setServiceFactory(serviceFactory);
        return command.execute(request, response);
    }

    @Override
    public void close() {
        serviceFactory.close();
    }
}
