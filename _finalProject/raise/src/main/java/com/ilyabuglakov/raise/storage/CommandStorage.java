package com.ilyabuglakov.raise.storage;

import com.ilyabuglakov.raise.command.impl.localization.ChangeLocalizationCommand;
import com.ilyabuglakov.raise.command.impl.login.LoginGetCommand;
import com.ilyabuglakov.raise.command.impl.login.LoginPostCommand;
import com.ilyabuglakov.raise.command.impl.test.TestCatalogPageCommand;
import com.ilyabuglakov.raise.command.impl.test.TestCreatorGetCommand;
import com.ilyabuglakov.raise.command.impl.test.TestCreatorSaveCommand;
import com.ilyabuglakov.raise.command.impl.test.TestPreviewPageCommand;
import com.ilyabuglakov.raise.command.impl.test.TestResultCommand;
import com.ilyabuglakov.raise.command.impl.test.TestingGetCommand;
import com.ilyabuglakov.raise.model.RequestMethod;
import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.impl.index.IndexGetCommand;
import com.ilyabuglakov.raise.command.impl.index.IndexPostCommand;
import com.ilyabuglakov.raise.command.impl.registration.RegistrationGetCommand;
import com.ilyabuglakov.raise.command.impl.registration.RegistrationPostCommand;

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

        addCommand(RequestMethod.GET, "root", new IndexGetCommand());
        addCommand(RequestMethod.GET, "index", new IndexGetCommand());
        addCommand(RequestMethod.POST, "index", new IndexPostCommand());
        addCommand(RequestMethod.GET, "registration", new RegistrationGetCommand());
        addCommand(RequestMethod.POST, "registration", new RegistrationPostCommand());
        addCommand(RequestMethod.GET, "changeLocale", new ChangeLocalizationCommand());
        addCommand(RequestMethod.GET, "test.creator", new TestCreatorGetCommand());
        addCommand(RequestMethod.POST, "test.creator.save", new TestCreatorSaveCommand());
        addCommand(RequestMethod.GET, "test.catalog", new TestCatalogPageCommand());
        addCommand(RequestMethod.GET, "test.testing", new TestingGetCommand());
        addCommand(RequestMethod.GET, "test.preview", new TestPreviewPageCommand());
        addCommand(RequestMethod.POST, "test.testing.result", new TestResultCommand());
        addCommand(RequestMethod.GET, "login", new LoginGetCommand());
        addCommand(RequestMethod.POST, "login", new LoginPostCommand());

    }

    private void addCommand(RequestMethod method, String property, Command command) {
        String uri = PropertiesStorage.getInstance().getLinks().getProperty(property);
        methodMap.get(method).put(uri, command);
    }

    public Command getCommand(String link, RequestMethod method) {
        return methodMap.get(method).get(link);
    }


}
