package com.ilyabuglakov.task06book.controller;

import com.ilyabuglakov.task06book.bean.CommandName;
import com.ilyabuglakov.task06book.controller.comand.Command;
import com.ilyabuglakov.task06book.storage.CommandMap;

public class CommandController {
    private static CommandController instance = new CommandController();
    private CommandMap repository = CommandMap.getInstance();

    private CommandController() {
    }

    /**
     * @return instance of singleton class
     */
    public static CommandController getInstance() {
        return instance;
    }

    /**
     * @param commandName - Enum name of command
     * @return command as Command class entity
     */
    public Command getCommand(CommandName commandName) {
        return repository.getCommand(commandName);
    }

    /**
     * Same as getCommand(), but instead of returning command
     * this method runs it.
     *
     * @param commandName - name of command to execute
     */
    public void executeCommand(CommandName commandName) {
        repository.getCommand(commandName).execute();
    }
}
