package com.ilyabuglakov.raise.storage;

import com.ilyabuglakov.raise.model.command.Command;
import com.ilyabuglakov.raise.model.command.impl.IndexCommand;
import com.ilyabuglakov.raise.model.command.impl.RegistrationCommand;
import com.ilyabuglakov.raise.service.property.PropertyParser;

import java.util.HashMap;
import java.util.Map;

public class CommandStorage {

    private Map<String, Command> commandMap;

    private static class InstanceHolder {
        public static CommandStorage INSTANCE = new CommandStorage();
    }

    public static CommandStorage getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private CommandStorage() {
        commandMap = new HashMap<>();
        PropertyParser links = PropertiesStorage.getInstance().getLinks();
        commandMap.put(links.getProperty("index"), new IndexCommand());
        commandMap.put(links.getProperty("registration"), new RegistrationCommand());
    }

    public Command getCommand(String link) {
        return commandMap.get(link);
    }


}
