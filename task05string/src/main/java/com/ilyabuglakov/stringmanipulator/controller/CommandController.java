package com.ilyabuglakov.stringmanipulator.controller;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.repository.CommandReppository;

public class CommandController {
    private CommandReppository repository = CommandReppository.getInstance();

    public void executeCommand(CommandName commandName){
        repository.getCommand(commandName).execute();
    }
}
