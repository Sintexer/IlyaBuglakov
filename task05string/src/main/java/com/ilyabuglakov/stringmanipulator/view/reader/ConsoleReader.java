package com.ilyabuglakov.stringmanipulator.view.reader;

import java.util.Scanner;

public class ConsoleReader implements Reader {
    private Scanner in = new Scanner(System.in);

    @Override
    public String readString() {
        return in.nextLine();
    }
}
