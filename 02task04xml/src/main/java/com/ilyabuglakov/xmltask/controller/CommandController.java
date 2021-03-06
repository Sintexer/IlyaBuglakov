package com.ilyabuglakov.xmltask.controller;


import com.ilyabuglakov.xmltask.controller.command.Command;
import com.ilyabuglakov.xmltask.model.CommandName;
import com.ilyabuglakov.xmltask.storage.CommandMap;

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
