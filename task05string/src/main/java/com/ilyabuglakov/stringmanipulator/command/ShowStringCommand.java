package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;

public class ShowStringCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        controller.getView().showMessage(MessageId.STRING);
        controller.getView().show(controller.getContent());
    }
}
