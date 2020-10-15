package com.ilyabuglakov.stringmanipulator.controller;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.service.input.InputValidator;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;
import com.ilyabuglakov.stringmanipulator.view.printer.ConsolePrinter;
import com.ilyabuglakov.stringmanipulator.view.printer.Printer;
import com.ilyabuglakov.stringmanipulator.view.reader.ConsoleReader;
import com.ilyabuglakov.stringmanipulator.view.reader.Reader;

/**
 * IOController encapsulates low level IO objects and provides
 * a simple interface to operate input and output
 */
public class IOController {

    private Reader in = new ConsoleReader();
    private Printer out = new ConsolePrinter();

    /**
     * Outputs object to output destination
     * @param object - object to show
     * @param <T> - any object
     */
    public <T> void show(T object) {
        out.show(object);
    }

    /**
     * Reads String from input and returns it
     * @return String from input source
     */
    public String readString() {
        return in.readString();
    }

    /**
     * Reads Integer from input and returns it
     * @return Integer from input source
     */
    public int readInt() {
        return readInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Reads Integer from input and returns it.
     * You can specify left bound of int number
     * @return Integer from input source greater or equal leftBound.
     */
    public int readInt(int leftBound) {
        return readInt(leftBound, Integer.MAX_VALUE);
    }

    /**
     * Reads Integer from input and returns it.
     * You can specify left and right bounds of int number
     * @return Integer from input source greater or equal leftBound
     * and lesser or equal rightBound.
     */
    public int readInt(int leftBound, int rightBound) {
        int val;
        while (true) {
            String input = in.readString();
            if (InputValidator.validInt(input)) {
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
