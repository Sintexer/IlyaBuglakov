package com.ilyabuglakov.stringmanipulator.controller;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.service.input.InputValidator;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;
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

    public int readInt() {
        return readInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int readInt(int leftBound) {
        return readInt(leftBound, Integer.MAX_VALUE);
    }

    public int readInt(int leftBound, int rightBound) {
        int val;
        while (true) {
            String input = in.readString();
            if(InputValidator.validInt(input)) {
                val = Integer.parseInt(input);
                if (val >= leftBound && val <= rightBound) {
                    break;
                }
            }
            ConsoleView.getInstance().showMessage(MessageId.WRONG_INT_INPUT);
            ConsoleView.getInstance().showMessage(MessageId.TRY_AGAIN);
        }
        return val;
    }

}
