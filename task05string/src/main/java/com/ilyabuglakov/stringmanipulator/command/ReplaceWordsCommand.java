package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;
import com.ilyabuglakov.stringmanipulator.service.string.ReplaceService;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;

public class ReplaceWordsCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();

        view.showMessage(MessageId.ENTER_POSITION);
        int length = view.getInt();
        view.showMessage(MessageId.ENTER_REPLACEMENT);
        String replacement = view.getString();
        ReplaceService replaceService = new ReplaceService();
        controller.setApplicationString(replaceService.replaceWords(
                controller.getApplicationString(),
                length,
                replacement));
    }
}
