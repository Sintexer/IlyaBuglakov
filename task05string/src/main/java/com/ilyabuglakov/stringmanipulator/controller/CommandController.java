package com.ilyabuglakov.stringmanipulator.controller;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.command.Command;
import com.ilyabuglakov.stringmanipulator.repository.CommandRepository;

public class CommandController {
    private static CommandController instance = new CommandController();
    private CommandRepository repository = CommandRepository.getInstance();

    private CommandController(){}

    public static CommandController getInstance() {
        return instance;
    }

    public Command getCommand(CommandName commandName){
        return repository.getCommand(commandName);
    }

    public void executeCommand(CommandName commandName){
        repository.getCommand(commandName).execute();
    }
}
