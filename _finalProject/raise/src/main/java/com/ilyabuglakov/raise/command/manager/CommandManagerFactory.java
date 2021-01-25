package com.ilyabuglakov.raise.command.manager;

import com.ilyabuglakov.raise.model.service.domain.factory.ServiceFactory;

public class CommandManagerFactory {

    public static CommandManager getCommandManager(ServiceFactory serviceFactory) {
        return new DatabaseCommandManager(serviceFactory);
    }

}
