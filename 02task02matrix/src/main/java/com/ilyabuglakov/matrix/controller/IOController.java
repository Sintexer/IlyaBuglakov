package com.ilyabuglakov.matrix.controller;

import com.ilyabuglakov.matrix.bean.MessageName;
import com.ilyabuglakov.matrix.service.console.ConsolePrinter;
import com.ilyabuglakov.matrix.service.console.ConsoleReader;
import com.ilyabuglakov.matrix.service.validator.IntValidator;
import com.ilyabuglakov.matrix.view.ConsoleView;

/**
 * IOController encapsulates low level IO objects and provides
 * a simple interface to operate input and output
 */
public class IOController {

    private ConsoleReader in = new ConsoleReader();
    private ConsolePrinter out = new ConsolePrinter();

    /**
     * Outputs object to output destination
     *
     * @param object - object to show
     * @param <T>    - any object
     */
    public <T> void show(T object) {
        out.show(object);
    }

    /**
     * Reads String from input and returns it
     *
     * @return String from input source
     */
    public String readString() {
        return in.readString();
    }

    /**
     * Reads Integer from input and returns it
     *
     * @return Integer from input source
     */
    public int readInt() {
        return readInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Reads Integer from input and returns it.
     * You can specify left bound of int number.
     *
     * @return Integer from input source greater or equal leftBound.
     */
    public int readInt(int leftBound) {
        return readInt(leftBound, Integer.MAX_VALUE);
    }

    /**
     * Reads Integer from input and returns it.
     * You can specify left and right bounds of int number.
     *
     * @return Integer from input source greater or equal leftBound
     * and lesser or equal rightBound.
     */
    public int readInt(int leftBound, int rightBound) {
        int val;
        while (true) {
            String input = in.readString();
            if (IntValidator.validInt(input)) {
                val = Integer.parseInt(input);
                if (val >= leftBound && val <= rightBound) {
                    break;
                }
            }
            ConsoleView.getInstance().showMessage(MessageName.WRONG_INT_INPUT);
            ConsoleView.getInstance().showMessage(MessageName.TRY_AGAIN);
        }
        return val;
    }

}
