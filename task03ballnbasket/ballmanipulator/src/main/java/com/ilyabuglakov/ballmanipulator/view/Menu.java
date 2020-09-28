package main.java.com.ilyabuglakov.ballmanipulator.view;

import main.java.com.ilyabuglakov.ballmanipulator.service.input.CheckedInput;
import main.java.com.ilyabuglakov.ballmanipulator.service.output.OutputDecorator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Menu class implement dialog with user via terminal.
 * Class entity can easily be completed by addOption method.
 * Last option always will be Exit option.
 * Menu can be continuous - after choosing an option menu will be opened again.
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-28
 */
public class Menu {

    private final String EXIT_OPTION = "0. Exit";
    private boolean continuous = false;
    private final CheckedInput checkedInput = new CheckedInput(new Scanner(System.in));

    private Map<String, Runnable> optionsMap = new LinkedHashMap<>();

    /**
     * Constructor specifies checkedInput onWrongInput action.
     */
    public Menu() {
        checkedInput.setOnWrongInput(() -> System.out.println("Wrong input"));
    }

    /**
     * Adds option with reaction to the options map.
     * @param option - the option name to be shown to user
     * @param action - action to be launched, when user chooses option.
     */
    public void addOption(String option, Runnable action) {
        optionsMap.put(option, action);
    }

    /**
     * Method starts the menu cycle
     */
    public void run() {
        List<String> options = new ArrayList<>(optionsMap.keySet());
        int index;
        do {
            System.out.println("Choose option:");
            OutputDecorator.showEnumeratedList(options);
            System.out.println(EXIT_OPTION);

            index = checkedInput.checkedInt(0, options.size() + 1);

            if (index != 0) {
                optionsMap.get(options.get(index - 1)).run();
            }
        } while (continuous && index != 0);
    }

    /**
     * Continuos menu won't collapse after choosing an option,
     * will only after choosing exit option
     * @param continuous
     */
    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
    }
}
