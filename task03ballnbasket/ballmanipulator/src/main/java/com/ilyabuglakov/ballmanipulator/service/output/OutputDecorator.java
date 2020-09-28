package main.java.com.ilyabuglakov.ballmanipulator.service.output;

import java.util.List;

/**
 * OutputDecorator is a service class, used to modify some information
 * and print it to the terminal.
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-28
 */
public class OutputDecorator {

    /**
     * Prints list in enumerated order
     * @param list - the list to print
     */
    public static void showEnumeratedList(List<?> list) {
        for (int i = 0; i < list.size(); ++i) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

}
