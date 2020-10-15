package com.ilyabuglakov.stringmanipulator.view.reader;

import java.util.Scanner;

/**
 * The implementation of Reader interfaces,that receives
 * input from console
 */
public class ConsoleReader implements Reader {
    private Scanner in = new Scanner(System.in);

    @Override
    public String readString() {
        return in.nextLine();
    }
}
