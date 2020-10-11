package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;

public class InputStringConsoleCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        controller.getView().showMessage(MessageId.ENTER_STRING);
        String input = controller.getView().getString();
        controller.setContent(input);
    }
}
