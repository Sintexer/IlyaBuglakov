package src.com.ilyabuglakov.arraymanipulator.view.reader;

import java.util.Scanner;

public class ConsoleReader implements Reader {
    private Scanner in = new Scanner(System.in);
    private static ConsoleReader reader;

    private ConsoleReader() {
    }

    public static ConsoleReader getInstance() {
        if (reader == null)
            reader = new ConsoleReader();
        return reader;
    }

    public int getInt() {
        return in.nextInt();
    }

    public String getString() {
        return in.nextLine();
    }

}
