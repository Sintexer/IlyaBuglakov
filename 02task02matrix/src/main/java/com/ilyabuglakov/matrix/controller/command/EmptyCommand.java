package com.ilyabuglakov.matrix.controller.command;

/**
 * Empty command, will do nothing, used for menu options, that used as stop-menu options.
 */
public class EmptyCommand implements Command {
    @Override
    public void execute() {

    }

    @Override
    public boolean applicableToEmptyMatrix() {
        return true;
    }
}