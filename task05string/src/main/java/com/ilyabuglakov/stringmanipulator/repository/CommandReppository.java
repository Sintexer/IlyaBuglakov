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

    public CommandReppository getInstance(){
        return instance;
    }

}
