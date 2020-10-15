package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;

/**
 * This command shows applicationString in console window
 */
public class ShowStringCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        controller.getView().showMessage(MessageId.STRING);
        controller.getView().show(controller.getApplicationString());
    }
}
