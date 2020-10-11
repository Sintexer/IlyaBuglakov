package com.ilyabuglakov.stringmanipulator.repository;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.command.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandReppository {
    private static CommandReppository instance = new CommandReppository();
    private static Map<CommandName, Command> commands = new HashMap<>();

    static {

    }

    private CommandReppository(){}

    public static CommandReppository getInstance(){
        return instance;
    }

    public Command getCommand(CommandName commandName){
        return commands.get(commandName);
    }

}
