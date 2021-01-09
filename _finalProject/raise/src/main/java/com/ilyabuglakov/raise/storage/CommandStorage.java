package com.ilyabuglakov.raise.storage;

import com.ilyabuglakov.raise.model.RequestMethod;
import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.impl.IndexCommand;
import com.ilyabuglakov.raise.command.impl.IndexPostCommand;
import com.ilyabuglakov.raise.command.impl.RegistrationCommand;
import com.ilyabuglakov.raise.command.impl.RegistrationPostCommand;
import com.ilyabuglakov.raise.service.property.PropertyParser;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class CommandStorage {

    private final EnumMap<RequestMethod, Map<String, Command>> methodMap;

    private static class InstanceHolder {
        public static final CommandStorage INSTANCE = new CommandStorage();
    }

    public static CommandStorage getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private CommandStorage() {
        methodMap = new EnumMap<>(RequestMethod.class);
        for (RequestMethod value : RequestMethod.values()) {
            methodMap.put(value, new HashMap<>());
        }

        PropertyParser links = PropertiesStorage.getInstance().getLinks();
        addCommand(RequestMethod.GET, "index", new IndexCommand());
        addCommand(RequestMethod.POST, "index", new IndexPostCommand());
        addCommand(RequestMethod.GET, "registration", new RegistrationCommand());
        addCommand(RequestMethod.POST, "registration", new RegistrationPostCommand());

    }

    private void addCommand(RequestMethod method, String property, Command command) {
        String uri = PropertiesStorage.getInstance().getLinks().getProperty(property);
        methodMap.get(method).put(uri, command);
    }

    public Command getCommand(String link, RequestMethod method) {
        return methodMap.get(method).get(link);
    }


}
