package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.model.CommandName;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArrayService;
import src.com.ilyabuglakov.arraymanipulator.service.juggedarray.JuggedArraySorter;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArraySearcher;
import src.com.ilyabuglakov.arraymanipulator.service.decorator.CollectionDecorator;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;
import src.com.ilyabuglakov.arraymanipulator.model.MessageId;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JuggedSortCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();

        ArrayService<Integer> arrayService = new ArrayService<>(); //TODO singleton
        ArraySearcher<Integer> arraySearcher = new ArraySearcher<>();
        Map<CommandName, Comparator<Array<Integer>>> comparatorMap = new HashMap<>();
        comparatorMap.put(CommandName.JUGGED_SORT_BY_SUM, Comparator.comparing(arrayService::sum));
        comparatorMap.put(CommandName.JUGGED_SORT_BY_MAX, Comparator.comparing(arraySearcher::findMax));
        comparatorMap.put(CommandName.JUGGED_SORT_BY_MIN, Comparator.comparing(arraySearcher::findMin));
        JuggedArraySorter<Integer> sorter = new JuggedArraySorter<>();
        List<CommandName> commandNames = new ArrayList<>(comparatorMap.keySet());

        view.showMessage(MessageId.JUGGED_SORT);
        view.show(CollectionDecorator.toEnumeratedList(view.getMessageList(commandNames)));

        int choice = view.readInt(1, commandNames.size());
        controller.setArray(sorter.sortBubble((JuggedArray<Integer>)ApplicationController.getInstance().getArray(),
                comparatorMap.get(commandNames.get(choice-1))));
    }
}
