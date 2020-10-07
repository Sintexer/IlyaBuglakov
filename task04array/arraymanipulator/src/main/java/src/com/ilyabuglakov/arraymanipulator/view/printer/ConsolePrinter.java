package src.com.ilyabuglakov.arraymanipulator.view.printer;

public class ConsolePrinter implements Printer {

    private static ConsolePrinter printer;

    private ConsolePrinter() {
    }

    public static ConsolePrinter getInstance() {
        if (printer == null)
            printer = new ConsolePrinter();
        return printer;
    }

    public void print(String message) {
        System.out.println(message);
    }

}
