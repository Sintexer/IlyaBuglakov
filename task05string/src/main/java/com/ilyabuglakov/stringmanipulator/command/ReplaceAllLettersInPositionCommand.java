package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;
import com.ilyabuglakov.stringmanipulator.service.string.ReplaceService;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;

public class ReplaceAllLettersInPositionCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        view.showMessage(MessageId.ENTER_POSITION);
        int position = controller.getView().getInt();
        view.showMessage(MessageId.ENTER_CHARACTER);
        char replacement = view.getChar();
        ReplaceService replaceService = new ReplaceService();
        controller.setApplicationString(replaceService.replaceSymbols(
                controller.getApplicationString(),
                position,
                replacement));
    }
}
