package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;

public class InputArrayCommand implements Command {
    @Override
    public void execute() {
        ArrayCreator<Integer> arrayCreator = new ArrayCreator<>();
        ConsoleView view = ApplicationController.getInstance().getView();

        Array<Integer> array = arrayCreator.createArray(view.readIntArray());
        ApplicationController.getInstance().setArray(array);
    }
}
