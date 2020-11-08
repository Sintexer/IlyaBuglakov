package com.ilyabuglakov.matrix.service.console;

/**
 * Simple Console printer
 */
public class ConsolePrinter {
    public <T> void show(T o) {
        System.out.println(o);
    }
}