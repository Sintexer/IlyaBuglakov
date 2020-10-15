package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;
import com.ilyabuglakov.stringmanipulator.service.string.ExcludeService;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * This command allows user to delete al words, which start with consonant
 * and have a certain length, defined by the user.
 */
public class DeleteConsonantWordsCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        controller.getView().showMessage(MessageId.ENTER_WORD_LENGTH);
        int length = controller.getView().getInt(0);
        controller.setApplicationString(ExcludeService.deleteAllByPatternIf(
                controller.getApplicationString(),
                Pattern.compile("\\b[^aioueyаиоуеюуяыэ\\s]\\w*\\b"),
                (MatchResult matcher) -> matcher.end() - matcher.start() == length));
    }
}
