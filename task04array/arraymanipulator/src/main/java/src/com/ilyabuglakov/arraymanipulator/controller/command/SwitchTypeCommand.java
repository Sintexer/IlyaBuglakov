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

public class SwitchTypeCommand implements Command {
    @Override
    public void execute() {
        ConsoleView view = ApplicationController.getInstance().getView();
        List<CommandName> types = new ArrayList<>(Arrays.asList(CommandName.SWITCH_TO_ARRAY, CommandName.SWITCH_TO_JUGGED_ARRAY));
        view.showMessage(MessageId.SWITCH_TYPE_CAPTURE);
        view.show(CollectionDecorator.toEnumeratedList(view.getMessageList(types)));
        int choice = view.readInt(1, types.size());
        ApplicationController.getInstance().setArray(null);
        CommandController.getInstance().getCommand(types.get(choice - 1)).execute();
    }

    @Override
    public boolean canBeAppliedWhenArrayIsNull() {
        return true;
    }
}
