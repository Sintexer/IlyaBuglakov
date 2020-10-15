package com.ilyabuglakov.stringmanipulator.view.printer;

/**
 * Implementation of Printer interface, that sends messages to console
 */
public class ConsolePrinter implements Printer {
    @Override
    public <T> void show(T o) {
        System.out.println(o);
    }
}
