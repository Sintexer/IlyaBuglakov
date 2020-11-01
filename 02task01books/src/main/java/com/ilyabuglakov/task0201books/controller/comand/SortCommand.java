package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.bean.CommandName;
import com.ilyabuglakov.task0201books.bean.MessageName;
import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.controller.CommandController;
import com.ilyabuglakov.task0201books.view.ConsoleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        view.showMessage(MessageName.CHOOSE_SORT);
        List<CommandName> options = new ArrayList<>(Arrays.asList(CommandName.SORT_PUBLICATIONS,
                CommandName.SORT_BOOKS,
                CommandName.SORT_MAGAZINES));
        view.showEnumeratedMessages(options);
        int choice = view.getInt(1, options.size());
        --choice;
        CommandController.getInstance().executeCommand(options.get(choice));
    }
}
