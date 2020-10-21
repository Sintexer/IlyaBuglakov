package com.ilyabuglakov.task06book.service.console;

import java.util.Scanner;

/**
 * Contains Scanner,that receives
 * input from console
 */
public class ConsoleReader {
    private Scanner in = new Scanner(System.in);

    public String readString() {
        return in.nextLine();
    }
}
