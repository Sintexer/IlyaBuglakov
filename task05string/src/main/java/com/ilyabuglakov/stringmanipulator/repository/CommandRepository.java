package com.ilyabuglakov.stringmanipulator.repository;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.command.Command;
import com.ilyabuglakov.stringmanipulator.command.EmptyCommand;
import com.ilyabuglakov.stringmanipulator.command.InputStringCommand;
import com.ilyabuglakov.stringmanipulator.command.InputStringConsoleCommand;
import com.ilyabuglakov.stringmanipulator.command.ShowStringCommand;

import java.util.EnumMap;

public class CommandRepository {
    private static CommandRepository instance = new CommandRepository();
    private static EnumMap<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    static {
        commands.put(CommandName.EXIT, new EmptyCommand());
        commands.put(CommandName.SHOW_STRING, new ShowStringCommand());
        commands.put(CommandName.INPUT_STRING, new InputStringCommand());
        commands.put(CommandName.INPUT_STRING_CONSOLE, new InputStringConsoleCommand());

    }

    private CommandRepository(){}

    public static CommandRepository getInstance(){
        return instance;
    }

    public Command getCommand(CommandName commandName){
        return commands.get(commandName);
    }

}
