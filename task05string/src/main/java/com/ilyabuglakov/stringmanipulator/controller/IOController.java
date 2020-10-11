package com.ilyabuglakov.stringmanipulator.controller;

import com.ilyabuglakov.stringmanipulator.view.printer.ConsolePrinter;
import com.ilyabuglakov.stringmanipulator.view.printer.Printer;
import com.ilyabuglakov.stringmanipulator.view.reader.ConsoleReader;
import com.ilyabuglakov.stringmanipulator.view.reader.Reader;

public class IOController {

    private Reader in = new ConsoleReader();
    private Printer out = new ConsolePrinter();

    public <T> void show (T object){
        out.show(object);
    }

    public String readString(){
        return in.readString();
    }

}
