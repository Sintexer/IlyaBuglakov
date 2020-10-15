package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;
import com.ilyabuglakov.stringmanipulator.service.string.ExcludeService;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class DeleteConsonantWordsCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        controller.getView().showMessage(MessageId.ENTER_WORD_LENGTH);
        int length = controller.getView().getInt(0);
        controller.setContent(ExcludeService.deleteAllByPatternIf(
                controller.getContent(),
                Pattern.compile("\\b[^aioueyаиоуеюуяыэ\\s]\\w*\\b"),
                (MatchResult matcher) -> matcher.end() - matcher.start() == length));
    }
}
