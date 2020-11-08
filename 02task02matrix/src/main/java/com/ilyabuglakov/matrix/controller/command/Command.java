package com.ilyabuglakov.matrix.controller.command;

/**
 * The Functional interface for applications menu commands implementation
 */
@FunctionalInterface
public interface Command {
    void execute();

    default boolean applicableToEmptyMatrix() {
        return false;
    }
}
