package main.java.com.ilyabuglakov.ballmanipulator.view;

import main.java.com.ilyabuglakov.ballmanipulator.service.input.CheckedInput;
import main.java.com.ilyabuglakov.ballmanipulator.service.output.OutputDecorator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private final String EXIT_OPTION = "0. Exit";
    private boolean continuous = false;
    CheckedInput checkedInput = new CheckedInput(new Scanner(System.in));

    Map<String, Runnable> optionsMap = new LinkedHashMap<>();

    public Menu(){
        checkedInput.setOnWrongInput(()-> System.out.println("Wrong input"));
    }

    public void addOption(String option, Runnable action){
        optionsMap.put(option, action);
    }

    public void run(){
        List<String> options = new ArrayList<>(optionsMap.keySet());
        int index;
        do {
            System.out.println("Choose option:");
            OutputDecorator.showEnumeratedList(options);
            System.out.println(EXIT_OPTION);

            index = checkedInput.checkedInt(0, options.size()+1);

            if (index != 0) {
                optionsMap.get(options.get(index - 1)).run();
            }
        } while (continuous && index!=0);
    }

    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
    }
}
