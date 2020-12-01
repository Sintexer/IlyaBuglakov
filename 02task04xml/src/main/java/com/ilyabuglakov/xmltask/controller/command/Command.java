package com.ilyabuglakov.xmltask.controller.command;


/**
 * The Functional interface for application menu commands implementation
 */
@FunctionalInterface
public interface Command {
    void execute();
}
