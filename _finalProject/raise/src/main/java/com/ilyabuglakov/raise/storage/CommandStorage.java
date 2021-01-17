package com.ilyabuglakov.raise.storage;

import com.ilyabuglakov.raise.command.localization.ChangeLocalizationCommand;
import com.ilyabuglakov.raise.command.test.TestCreatorGetCommand;
import com.ilyabuglakov.raise.command.test.TestCreatorSaveCommand;
import com.ilyabuglakov.raise.model.RequestMethod;
import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.index.IndexGetCommand;
import com.ilyabuglakov.raise.command.index.IndexPostCommand;
import com.ilyabuglakov.raise.command.registration.RegistrationGetCommand;
import com.ilyabuglakov.raise.command.registration.RegistrationPostCommand;

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

        addCommand(RequestMethod.GET, "index", new IndexGetCommand());
        addCommand(RequestMethod.POST, "index", new IndexPostCommand());
        addCommand(RequestMethod.GET, "registration", new RegistrationGetCommand());
        addCommand(RequestMethod.POST, "registration", new RegistrationPostCommand());
        addCommand(RequestMethod.GET, "changeLocale", new ChangeLocalizationCommand());
        addCommand(RequestMethod.GET, "test.creator", new TestCreatorGetCommand());
        addCommand(RequestMethod.POST, "test.creator.save", new TestCreatorSaveCommand());

    }

    private void addCommand(RequestMethod method, String property, Command command) {
        String uri = PropertiesStorage.getInstance().getLinks().getProperty(property);
        methodMap.get(method).put(uri, command);
    }

    public Command getCommand(String link, RequestMethod method) {
        return methodMap.get(method).get(link);
    }


}
