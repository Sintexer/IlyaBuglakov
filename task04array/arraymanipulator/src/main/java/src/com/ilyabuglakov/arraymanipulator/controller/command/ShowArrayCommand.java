package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.ArrayInterface;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;

public class ShowArrayCommand implements Command {
    @Override
    public void execute() {
        ConsoleView view = ApplicationController.getInstance().getView();
        ArrayInterface<Integer> array = ApplicationController
                .getInstance()
                .getArray();
        view.show(array);
    }
}
