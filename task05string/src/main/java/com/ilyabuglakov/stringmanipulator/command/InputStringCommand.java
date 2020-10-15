package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;
import com.ilyabuglakov.stringmanipulator.controller.CommandController;

import java.util.Arrays;
import java.util.List;

public class InputStringCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        List<CommandName> options = Arrays.asList(
                CommandName.INPUT_STRING_CONSOLE,
                CommandName.INPUT_STRING_FILE
        );
        controller.getView().showEnumeratedMessages(options);
        int choice = controller.getView().getInt(1, options.size());
        CommandController.getInstance().executeCommand(options.get(choice - 1));
    }
}
