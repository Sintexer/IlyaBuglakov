package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.ArrayInterface;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArraySearcher;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;

public class FindMinCommand implements Command {
    @Override
    public void execute() {
        ConsoleView view = ApplicationController.getInstance().getView();
        ArrayInterface<Integer> array = ApplicationController.getInstance().getArray();
        ArraySearcher<Integer> searcher = new ArraySearcher<>();
        view.show(searcher.findMin(array));
    }
}
