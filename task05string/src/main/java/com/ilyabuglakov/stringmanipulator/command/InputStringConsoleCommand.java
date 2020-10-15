package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;

/**
 * This command allows user to enter the applicationString from console
 */
public class InputStringConsoleCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        controller.getView().showMessage(MessageId.ENTER_STRING);
        String input = controller.getView().getString();
        controller.setApplicationString(input);
    }
}
