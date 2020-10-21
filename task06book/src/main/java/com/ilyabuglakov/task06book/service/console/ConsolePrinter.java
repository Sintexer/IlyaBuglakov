package com.ilyabuglakov.task06book.service.console;

/**
 * Simple Console printer
 */
public class ConsolePrinter {
    public <T> void show(T o) {
        System.out.println(o);
    }
}