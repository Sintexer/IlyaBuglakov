package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.service.ArrayService;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArraySearcher;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;

import java.util.Comparator;

public class BinarySearchCommand implements Command {
    @Override
    public void execute() {
        ConsoleView view = ApplicationController.getInstance().getView();
        ArrayService<Integer> service = new ArrayService<>();
        Array<Integer> array = (Array<Integer>) ApplicationController.getInstance().getArray();
        if (!service.isSorted(array, Comparator.naturalOrder())) {
            view.showMessage(MessageId.ARRAY_IS_NOT_SORTED);
            return;
        }
        view.showMessage(MessageId.INPUT_INT);
        Integer element = view.readInt();


        ArraySearcher<Integer> searcher = new ArraySearcher<>();
        view.show(searcher.binarySearch(array, element));
    }
}
