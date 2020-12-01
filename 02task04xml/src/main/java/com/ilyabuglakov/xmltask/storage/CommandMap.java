package com.ilyabuglakov.xmltask.storage;

import com.ilyabuglakov.xmltask.controller.command.Command;
import com.ilyabuglakov.xmltask.controller.command.EmptyCommand;
import com.ilyabuglakov.xmltask.model.CommandName;

import java.util.EnumMap;

public class CommandMap {
    private static CommandMap instance = new CommandMap();
    private static EnumMap<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    static {
        commands.put(CommandName.EXIT, new EmptyCommand());
    }

    private CommandMap() {
    }

    /**
     * Returns instance of singleton class
     *
     * @return CommandMap instance
     */
    public static CommandMap getInstance() {
        return instance;
    }

    /**
     * Returns Command entity associated with CommandName
     *
     * @param commandName enum name of command
     * @return Command entity, associated with enum name
     */
    public Command getCommand(CommandName commandName) {
        return commands.get(commandName);
    }
}
