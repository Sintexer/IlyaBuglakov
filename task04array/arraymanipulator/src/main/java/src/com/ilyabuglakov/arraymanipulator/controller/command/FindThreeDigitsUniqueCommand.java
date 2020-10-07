package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.repository.ArrayInterface;
import src.com.ilyabuglakov.arraymanipulator.service.NumberService;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArraySearcher;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;

public class FindThreeDigitsUniqueCommand implements Command {
    @Override
    public void execute() {
        ConsoleView view = ApplicationController.getInstance().getView();
        ArrayInterface<Integer> array = ApplicationController.getInstance().getArray();
        ArraySearcher<Integer> searcher = new ArraySearcher<>();
        NumberService service = new NumberService();
        view.show(searcher.findByPredicate((Array<Integer>)array, service::isThreeDigitUnique));
    }
}
