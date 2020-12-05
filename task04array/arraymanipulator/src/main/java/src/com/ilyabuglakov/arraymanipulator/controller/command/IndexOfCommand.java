package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.ArrayInterface;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArraySearcher;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;
import src.com.ilyabuglakov.arraymanipulator.model.MessageId;

public class IndexOfCommand implements Command {
    @Override
    public void execute() {
        ConsoleView view = ApplicationController.getInstance().getView();
        view.showMessage(MessageId.INPUT_INT);
        Integer element = view.readInt();
        ArrayInterface<Integer> array = ApplicationController.getInstance().getArray();
        ArraySearcher<Integer> searcher = new ArraySearcher<>();
        view.show(searcher.indexOf(array, element));
    }
}
