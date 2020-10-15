package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;
import com.ilyabuglakov.stringmanipulator.service.string.ReplaceService;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;

public class CorrectMistakesCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        view.showMessage(MessageId.ENTER_CHAR_BEFORE_MISTAKE);
        char beforeMistake = view.getChar();
        view.showMessage(MessageId.ENTER_MISTAKE);
        char mistake = view.getChar();
        view.showMessage(MessageId.ENTER_CORRECT_CHAR);
        char replacement = view.getChar();
        ReplaceService replaceService = new ReplaceService();
        controller.setApplicationString(replaceService.replaceMistake(
                controller.getApplicationString(),
                beforeMistake,
                mistake,
                replacement));
    }
}
