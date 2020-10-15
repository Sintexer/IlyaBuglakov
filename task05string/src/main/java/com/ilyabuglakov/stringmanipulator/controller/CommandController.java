package com.ilyabuglakov.stringmanipulator.controller;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.command.Command;
import com.ilyabuglakov.stringmanipulator.repository.CommandRepository;

/**
 * CommandController hides CommandRepository inside himself
 * and delegates and decorates method calls to him.
 * Implemented as singleton.
 */
public class CommandController {
    private static CommandController instance = new CommandController();
    private CommandRepository repository = CommandRepository.getInstance();

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
     * @param commandName - name of command to execute
     */
    public void executeCommand(CommandName commandName) {
        repository.getCommand(commandName).execute();
    }
}
