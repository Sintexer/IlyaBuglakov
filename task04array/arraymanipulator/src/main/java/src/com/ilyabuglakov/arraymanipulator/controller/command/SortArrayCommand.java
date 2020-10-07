package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.controller.CommandName;
import src.com.ilyabuglakov.arraymanipulator.controller.SortController;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArraySorter;
import src.com.ilyabuglakov.arraymanipulator.service.decorator.CollectionDecorator;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;

import java.util.ArrayList;
import java.util.List;

public class SortArrayCommand implements Command {
    private src.com.ilyabuglakov.arraymanipulator.repository.Array<Integer> Array;

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();

        ArraySorter<Integer> sorter = new ArraySorter<>();
        SortController<Integer> sortController = new SortController<>();
        sortController.addSort(CommandName.SORT_ARRAY_SHELL, sorter::sortShell);
        sortController.addSort(CommandName.SORT_ARRAY_INSERTION, sorter::sortInsertion);
        sortController.addSort(CommandName.SORT_ARRAY_MERGE, sorter::sortMerge);
        List<CommandName> commandNames = new ArrayList<>(sortController.getCommandNamesList());

        view.showMessage(MessageId.SORT_ARRAY);
        view.show(CollectionDecorator.toEnumeratedList(view.getMessageList(commandNames)));

        int choice = view.readInt(1, commandNames.size());
        controller.setArray(sortController
                .getSort(commandNames
                        .get(choice-1))
                .apply((Array<Integer>)controller.getArray()));

    }
}
