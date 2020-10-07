package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.controller.CommandController;
import src.com.ilyabuglakov.arraymanipulator.controller.CommandName;
import src.com.ilyabuglakov.arraymanipulator.service.decorator.CollectionDecorator;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FillJuggedArrayCommand implements Command {
    @Override
    public void execute() {
        ConsoleView view = ApplicationController.getInstance().getView();
        List<CommandName> commandNames = new ArrayList<>(
                Arrays.asList(CommandName.INPUT_JUGGED_ARRAY,
                        CommandName.RANDOM_JUGGED_ARRAY));
        view.showMessage(MessageId.FILL_JUGGED_ARRAY);
        view.show(CollectionDecorator.toEnumeratedList(view.getMessageList(commandNames)));
        int choice = view.readInt(1, commandNames.size());
        CommandController.getInstance().getCommand(commandNames.get(choice-1)).execute();

    }

    @Override
    public boolean canBeAppliedWhenArrayIsNull() {
        return true;
    }
}
