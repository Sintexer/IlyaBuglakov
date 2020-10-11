package com.ilyabuglakov.stringmanipulator.view.printer;

public class ConsolePrinter implements Printer {
    @Override
    public <T> void show(T o) {
        System.out.println(o);
    }
}
